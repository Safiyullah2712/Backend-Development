class BankAccount {

    // Properties
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = 0.0;
    }

    // Method: Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited successfully");
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    // Method: Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully");
        } else {
            System.out.println("Insufficient balance or invalid amount");
        }
    }

    // Method: Check balance
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Optional method: Show account details
    public void showAccountInfo() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Balance        : " + balance);
    }
}

public class Main4 {
    public static void main(String[] args) {

        BankAccount acc = new BankAccount("ACC701", "Lelin", "Savings");

        acc.deposit(5000);
        acc.withdraw(1500);
        acc.checkBalance();

        System.out.println();

        acc.showAccountInfo();
    }
}
