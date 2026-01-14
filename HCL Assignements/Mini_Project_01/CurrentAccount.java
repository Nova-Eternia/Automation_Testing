package Mini_Project_01;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    // Method Overriding
    public void withdraw(double amt) {
        if (balance - amt >= -5000) {
            balance -= amt;
            System.out.println("Withdrawal Successful");
        } else {
            System.out.println("Overdraft Limit Exceeded");
        }
    }

    public void displayAccountType() {
        System.out.println("Account Type: Current Account");
    }
}
