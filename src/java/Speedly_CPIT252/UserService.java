import java.util.ArrayList;

public class UserService {

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private InputHelper input;

    public UserService(InputHelper input) {
        this.input = input;

        Employee defaultEmployee = (Employee) UserFactory.createUser(
                "employee",
                "Speedly Staff",
                "0500000000",
                "staff@speedly.com",
                "staff123"
        );

        employees.add(defaultEmployee);
    }

    public Customer registerCustomer() {
        System.out.println("\n===== REGISTER CUSTOMER =====");

        String name = input.readName();
        String phone = input.readPhone();
        String email = input.readEmail();
        String password = input.readPassword();

        Customer customer = (Customer) UserFactory.createUser(
                "customer",
                name,
                phone,
                email,
                password
        );

        customers.add(customer);

        System.out.println("Customer registered successfully.");
        return customer;
    }

    public Employee registerEmployee() {
        System.out.println("\n===== REGISTER EMPLOYEE =====");

        String name = input.readName();
        String phone = input.readPhone();
        String email = input.readEmail();
        String password = input.readPassword();

        Employee employee = (Employee) UserFactory.createUser(
                "employee",
                name,
                phone,
                email,
                password
        );

        employees.add(employee);

        System.out.println("Employee registered successfully.");
        return employee;
    }

    private Customer loginCustomer() {
        System.out.println("\n===== CUSTOMER LOGIN =====");

        String emailOrPhone = input.readLine("Enter email or phone: ");
        String password = input.readPassword();

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

    public Customer customerAccountProcess() {
        String hasAccount = input.readYesNo("\nDo you have a customer account? yes/no: ");

        if (hasAccount.equals("no")) {
            return registerCustomer();
        }

        if (!hasCustomers()) {
            System.out.println("No customers registered. Please create a new account.");
            return registerCustomer();
        }

        Customer customer = null;

        while (customer == null) {
            customer = loginCustomer();
        }

        return customer;
    }

    public Employee getDefaultEmployee() {
        return employees.get(0);
    }

    public void showUsers() {
        System.out.println("\n===== CUSTOMERS =====");

        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }

        System.out.println("\n===== EMPLOYEES =====");

        if (employees.isEmpty()) {
            System.out.println("No employees registered.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public boolean hasCustomers() {
        return !customers.isEmpty();
    }
}