import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class A2Question2 {
    
    // Regex patterns for validation
    private static final String MOBILE_PATTERN = "^[0-9]{10}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String USERNAME_PATTERN = "^[A-Za-z0-9_]{5,15}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean exit = false;
        
        try {
            while (!exit) {
                displayMenu();
                int choice = getValidChoice();
                
                switch (choice) {
                    case 1:
                        validateMobileNumber();
                        break;
                    case 2:
                        validateEmailID();
                        break;
                    case 3:
                        validateUsername();
                        break;
                    case 4:
                        validatePassword();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("\n=== Thank you for using Input Validation Program ===");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select between 1-5.");
                }
                
                if (!exit) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n========== INPUT VALIDATION MENU ==========");
        System.out.println("1. Validate Mobile Number");
        System.out.println("2. Validate Email ID");
        System.out.println("3. Validate Username");
        System.out.println("4. Validate Password");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }
    
    private static int getValidChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number between 1-5.");
            return -1;
        }
    }
    
    private static void validateMobileNumber() {
        try {
            System.out.print("\nEnter Mobile Number (10 digits): ");
            String mobile = scanner.nextLine().trim();
            
            if (mobile.isEmpty()) {
                throw new IllegalArgumentException("Mobile number cannot be empty.");
            }
            
            if (isValid(mobile, MOBILE_PATTERN)) {
                System.out.println("✓ Welcome! Your mobile number '" + mobile + "' is valid.");
            } else {
                System.out.println("✗ Invalid Input! Mobile number must contain exactly 10 digits.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ An error occurred: " + e.getMessage());
        }
    }
    
    private static void validateEmailID() {
        try {
            System.out.print("\nEnter Email ID: ");
            String email = scanner.nextLine().trim();
            
            if (email.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty.");
            }
            
            if (isValid(email, EMAIL_PATTERN)) {
                System.out.println("✓ Welcome! Your email '" + email + "' is valid.");
            } else {
                System.out.println("✗ Invalid Input! Please enter a valid email format (e.g., user@example.com).");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ An error occurred: " + e.getMessage());
        }
    }
    
    private static void validateUsername() {
        try {
            System.out.print("\nEnter Username (5-15 alphanumeric/underscore): ");
            String username = scanner.nextLine().trim();
            
            if (username.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty.");
            }
            
            if (isValid(username, USERNAME_PATTERN)) {
                System.out.println("✓ Welcome! Your username '" + username + "' is valid.");
            } else {
                System.out.println("✗ Invalid Input! Username must be 5-15 characters long and contain only letters, numbers, and underscores.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ An error occurred: " + e.getMessage());
        }
    }
    
    private static void validatePassword() {
        try {
            System.out.print("\nEnter Password (min 8 chars, 1 uppercase, 1 lowercase, 1 digit, 1 special char): ");
            String password = scanner.nextLine().trim();
            
            if (password.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty.");
            }
            
            if (isValid(password, PASSWORD_PATTERN)) {
                System.out.println("✓ Welcome! Your password is valid and secure.");
            } else {
                System.out.println("✗ Invalid Input! Password must contain:");
                System.out.println("  - At least 8 characters");
                System.out.println("  - At least one uppercase letter (A-Z)");
                System.out.println("  - At least one lowercase letter (a-z)");
                System.out.println("  - At least one digit (0-9)");
                System.out.println("  - At least one special character (@$!%*?&)");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ An error occurred: " + e.getMessage());
        }
    }
    
    private static boolean isValid(String input, String pattern) {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(input);
            return m.matches();
        } catch (Exception e) {
            System.out.println("✗ Error in pattern matching: " + e.getMessage());
            return false;
        }
    }
}
