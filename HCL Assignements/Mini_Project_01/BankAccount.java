package Mini_Project_01;

import java.io.Serializable;

public abstract class BankAccount implements Serializable {
    protected int accNo;
    protected String name;
    protected double balance;

    public BankAccount(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    // Abstract method
    public abstract void displayAccountType();
    public abstract void withdraw(double amt);

    // Method Overloading
    public void deposit(double amt) {
        balance += amt;
        System.out.println("Amount Deposited");
    }

    public void deposit(int amt) {
        balance += amt;
        System.out.println("Amount Deposited");
    }

    public void showDetails() {
        System.out.println("Account No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }
}

