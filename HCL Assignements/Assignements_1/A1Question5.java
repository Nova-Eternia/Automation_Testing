
/**Design a console-based Bank Management Program in Java that allows a user to create a 
* bank account and perform basic banking operations such as deposit, withdrawal, balance 
* enquiry, and account details display. The program should handle exceptions as required. 
*/

import java.util.Scanner;

public class A1Question5 {
    String bankName;
    String accountHolderName;
    int accountNumber;
    String IFSC;
    int balance;

    Fifth(String bankname, String name, int accNumber, String ifsc, int money) {
        bankName = bankname;
        accountHolderName = name;
        balance = money;
        accountNumber = accNumber;
        IFSC = ifsc;
    }

    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    public int withdrawal(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return balance;
        } else {
            System.out.println("Insufficient Balance");
            return balance;
        }
    }

    public void Bank_Detail() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Bank Name : " + bankName);
        System.out.println("Account Holder Name : " + accountHolderName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("IFSC Code : " + IFSC);
        System.out.println("Bank Balance : " + balance);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the Bank Details : \n");
        System.out.print("Enter Bank Name: ");
        String bankName = sc.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = sc.nextLine();
        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        System.out.print("Enter IFSC CODE : ");
        String IFSC = sc.next();
        System.out.print("Enter Balance : ");
        int balance = sc.nextInt();
        Fifth obj = new Fifth(bankName, accountHolderName, accountNumber, IFSC, balance);
        int choice;
        do {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Account Details");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    int amount = sc.nextInt();
                    int newBalance = obj.deposit(amount);
                    System.out.println("Deposited " + amount + ". New Balance: " + newBalance);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    int amount = sc.nextInt();
                    int newBalance = obj.withdrawal(amount);
                    System.out.println("Withdrew " + amount + ". New Balance: " + newBalance);
                }
                case 3 -> System.out.println("Current Balance: " + obj.balance);
                case 4 -> obj.Bank_Detail();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }
}
