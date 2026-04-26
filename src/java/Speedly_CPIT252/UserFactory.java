public class UserFactory {

    public static User createUser(String type, String name, String phoneNumber, String email) {

        switch (type.toLowerCase()) {
            case "customer":
                return new Customer(name, phoneNumber, email);

            case "employee":
                return new Employee(name, phoneNumber, email);

            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}
