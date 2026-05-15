import java.util.Scanner;

public class InputHelper {

    private Scanner input = new Scanner(System.in);

    public int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public String readName() {
        while (true) {
            System.out.print("Enter name: ");
            String name = input.nextLine();

            if (!name.trim().isEmpty()) {
                return name;
            }

            System.out.println("Name cannot be empty.");
        }
    }

    public String readPhone() {
        while (true) {
            System.out.print("Enter phone number: ");
            String phone = input.nextLine();

            if (phone.length() == 10 && phone.startsWith("05")) {
                return phone;
            }

            System.out.println("Invalid phone number. It must start with 05 and contain 10 digits.");
        }
    }

    public String readEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = input.nextLine();

            if (email.contains("@") && email.contains(".")) {
                return email;
            }

            System.out.println("Invalid email format. Example: name@gmail.com");
        }
    }

    public String readPassword() {
        while (true) {
            System.out.print("Enter password: ");
            String password = input.nextLine();

            if (password.length() >= 4) {
                return password;
            }

            System.out.println("Password must be at least 4 characters.");
        }
    }

    public String readYesNo(String message) {
        while (true) {
            System.out.print(message);
            String answer = input.nextLine().trim().toLowerCase();

            if (answer.equals("yes") || answer.equals("no")) {
                return answer;
            }

            System.out.println("Please enter yes or no.");
        }
    }

    public String readLine(String message) {
        System.out.print(message);
        return input.nextLine();
    }

    public void close() {
        input.close();
    }
}