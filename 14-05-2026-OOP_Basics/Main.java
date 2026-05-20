class MobileAccount {

    // hidden balance
    private double balance = 100.0;

    // Method to recharge
    public void recharge(double amount) {

        if (amount > 0) {
            balance += amount;
            System.out.println("Recharge successful");
        } else {
            System.out.println("Invalid amount");
        }
    }

    // Method to make a call
    public void makeCall(double cost) {

        if (cost <= balance) {
            balance -= cost;
            System.out.println("Call connected");
        } else {
            System.out.println("Insufficient balance");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

public class Main {
    public static void main(String[] args) {

        MobileAccount user = new MobileAccount();

        user.recharge(50);
        user.makeCall(20);
        user.checkBalance();

        // user.balance = 1000;
    }
}
