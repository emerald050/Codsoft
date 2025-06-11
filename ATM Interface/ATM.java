import java.util.Scanner;

// 1. Class to represent the Bank Account
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative. Setting to ₹0.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    // 3. Method to deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ ₹" + amount + " deposited successfully.");
        } else {
            System.out.println("❌ Deposit amount must be greater than 0.");
        }
    }

    // 3. Method to withdraw
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Withdrawal amount must be greater than 0.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance. Your current balance is ₹" + balance);
        } else {
            balance -= amount;
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
        }
    }

    // 3. Method to check balance
    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }
}

// 1. Class to represent the ATM machine
class ATM1 {
    private BankAccount account;
    private Scanner scanner;

    // 5. Connect to BankAccount class
    public ATM1(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // 2. User interface loop
    public void start() {
        int choice;

        do {
            showMenu();
            System.out.print("Enter your choice (1-4): ");

            while (!scanner.hasNextInt()) {
                System.out.println("❌ Please enter a number between 1 and 4.");
                scanner.next(); // Discard invalid input
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid option. Try again.");
            }

            System.out.println(); // Empty line for better spacing

        } while (choice != 4);
    }

    private void showMenu() {
        System.out.println("📟 === ATM MENU ===");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = getValidDouble();
        account.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = getValidDouble();
        account.deposit(amount);
    }

    private double getValidDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("❌ Please enter a valid number.");
            scanner.next(); // discard invalid input
        }
        return scanner.nextDouble();
    }
}

// Main class to run the program
public class ATM {
    public static void main(String[] args) {
        // 4. Create a bank account with an initial balance of ₹1000
        BankAccount myAccount = new BankAccount(1000.0);

        // 5. Connect ATM with user's bank account
        ATM1 atmMachine = new ATM1(myAccount);

        // 2. Start the ATM user interface
        atmMachine.start();
    }
}
