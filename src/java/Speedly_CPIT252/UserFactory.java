public class UserFactory {

    public static User createUser(String type, String name, String phoneNumber, String email, String password) {

        if (type == null) {
            throw new IllegalArgumentException("User type cannot be null");
        }

        switch (type.toLowerCase()) {
            case "customer":
                return new Customer(name, phoneNumber, email, password);

            case "employee":
                return new Employee(name, phoneNumber, email, password);

            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}