package Mini_Project_01;

import java.util.*;

public class BankMain {
    static Scanner sc = new Scanner(System.in);
    static BankAccount acc;

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("\n--- BANK MANAGEMENT SYSTEM ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Account Details");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: balance(); break;
                case 5: details(); break;
                case 6: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

    // Create Account
    static void createAccount() throws Exception {
        System.out.print("Enter Account No: ");
        int no = sc.nextInt();
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.print("Enter Initial Balance: ");
        double bal = sc.nextDouble();
        System.out.print("1. Saving  2. Current: ");
        int type = sc.nextInt();

        if (type == 1)
            acc = new SavingAccount(no, name, bal);
        else
            acc = new CurrentAccount(no, name, bal);

        FileHandler.save(acc);
        System.out.println("Account Created Successfully");
    }

    // Deposit
    static void deposit() throws Exception {
        acc = FileHandler.load();
        System.out.print("Enter Amount: ");
        double amt = sc.nextDouble();
        acc.deposit(amt);
        FileHandler.save(acc);
    }

    // Withdraw
    static void withdraw() throws Exception {
        acc = FileHandler.load();
        System.out.print("Enter Amount: ");
        double amt = sc.nextDouble();
        acc.withdraw(amt);   // Runtime Polymorphism
        FileHandler.save(acc);
    }

    // Balance Enquiry
    static void balance() throws Exception {
        acc = FileHandler.load();
        System.out.println("Current Balance: " + acc.balance);
    }

    // Account Details
    static void details() throws Exception {
        acc = FileHandler.load();
        acc.displayAccountType();
        acc.showDetails();
    }
}
