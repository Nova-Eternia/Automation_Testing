package Mini_Project_01;

public class SavingAccount extends BankAccount {

    public SavingAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    // Method Overriding
    public void withdraw(double amt) {
        if (amt <= balance) {
            balance -= amt;
            System.out.println("Withdrawal Successful");
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public void displayAccountType() {
        System.out.println("Account Type: Saving Account");
    }
}

