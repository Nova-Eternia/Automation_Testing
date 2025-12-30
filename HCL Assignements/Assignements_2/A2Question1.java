

import java.util.*;

// Interface for Banking Operations
interface BankOperations {
    void deposit(double amount) throws InvalidAmountException;
    void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException;
    void checkBalance();
}

// Interface for Customer Operations
interface CustomerOperations {
    void updateDetails(String name, int accNo);
    void displayDetails();
}

// Custom Exception: Invalid deposit/withdraw amount exception
class InvalidAmountException extends Exception {
    public InvalidAmountException(String msg) {
        super(msg);
    }
}

// Custom Exception: Insufficient balance exception
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

// Class implementing both Interfaces
class BankAccount implements BankOperations, CustomerOperations {

    private String customerName;
    private int accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String customerName, int accountNumber, double balance) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Banking operations implementation
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println(amount + " deposited successfully.");
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdraw amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal failed! Insufficient balance.");
        }
        balance -= amount;
        System.out.println(amount + " withdrawn successfully.");
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Customer operations implementation
    public void updateDetails(String name, int accNo) {
        this.customerName = name;
        this.accountNumber = accNo;
        System.out.println("Customer details updated successfully.");
    }

    public void displayDetails() {
        System.out.println("--- Customer Details ---");
        System.out.println("Name: " + customerName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

// Main class
public class A2Question1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount acc = new BankAccount("Rahul", 12345, 5000);

        try {
            acc.displayDetails();
            acc.deposit(1000);
            acc.checkBalance();
            acc.withdraw(2000);
            acc.checkBalance();
            acc.withdraw(5000); // will throw exception
        }
        catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        sc.close();
    }
}
