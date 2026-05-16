package Speedly_CPIT252.AppService;

import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Factory.UserFactory;
import Speedly_CPIT252.UserInput.InputValidator;

import java.util.ArrayList;

//This class handles customer registration, login, and stored users
public class UserService {

    //list to store customers in the system
    private ArrayList<Customer> customers = new ArrayList<>();

    //The staff account is created by default because staff already exists in the system
    private Staff defaultStaff;

    //InputValidator is used to read and validate user input
    private InputValidator input;

    //Constructor that receives the input validator and creates a default staff account
    public UserService(InputValidator input) {
        this.input = input;

        //create a default staff account that already exists in the system
        User staff = UserFactory.createUser("staff", "Speedly Staff",
                "0500000000", "staff@speedly.com", "staff123");
        defaultStaff = (Staff) staff;
    }

    //register a new customer and adds the customer to the customers list
    public Customer registerCustomer() {
        System.out.println("\n===== REGISTER CUSTOMER =====");

        String name = input.readName();
        String phone = input.readPhone();
        String email = input.readEmail();
        String password = input.readPassword();

        //create the customer using UserFactory
        User customer = UserFactory.createUser("customer", name,
                phone, email, password);

        customers.add((Customer) customer);
        System.out.println("Customer registered successfully.");
        return (Customer) customer;
    }

    //Logs in a customer using email or phone number and password
    private Customer loginCustomer() {
        System.out.println("\n===== CUSTOMER LOGIN =====");

        String emailOrPhone = input.readLine("Enter email or phone: ");
        String password = input.readPassword();

        //search for a customer with matching email/phone and password
        for (Customer customer : customers) {
            boolean sameEmail = customer.getEmail().equalsIgnoreCase(emailOrPhone);
            boolean samePhone = customer.getPhoneNumber().equals(emailOrPhone);
            boolean samePassword = customer.getPassword().equals(password);

            if ((sameEmail || samePhone) && samePassword) {
                System.out.println("Login successful.");
                return customer;
            }
        }

        System.out.println("Invalid login information.");
        return null;
    }

    //handles the customer account process by either registering or logging in
    public Customer customerAccountProcess() {
        String hasAccount = input.readYesNo("\nDo you have a customer account? yes/no: ");

        //if the customer does not have an account, create a new one
        if (hasAccount.equals("no")) {
            return registerCustomer();
        }

        //if the customer says they have an account but no customers exist, create a new account
        if (!hasCustomers()) {
            System.out.println("No customers registered. Please create a new account!");
            return registerCustomer();
        }

        Customer customer = null;

        //keep asking the customer to log in until the information is correct.
        while (customer == null) {
            customer = loginCustomer();
        }
        return customer;
    }

    //returns the default Staff account
    public Staff getDefaultStaff() {
        return defaultStaff;
    }

    //displays all registered customers nd the default staff
    public void showUsers() {
        System.out.println("\n===== CUSTOMERS =====");

        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
        System.out.println("\n===== STAFFS =====");
        System.out.println(defaultStaff);
    }

    //check if there is at least one registered customer
    public boolean hasCustomers() {
        return !customers.isEmpty();
    }
}