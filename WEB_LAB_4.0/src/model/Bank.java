package model;

public class Bank {
    private static int id;
    private int ID;
    private String firstName;
    private String secondName;
    private int accountNumber;
    private double balance;
    private double bankBalance;
    private double repository;
    private double amount;

    public Bank(double bankBalance, double repository) {
        this.bankBalance = bankBalance;
        this.repository = repository;
    }

    public Bank(String firstName, String secondName, int accountNumber, double balance,
                double bankBalance, double repository) {
        this.ID = id++;
        this.firstName = firstName;
        this.secondName = secondName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.repository = repository;
        this.bankBalance = bankBalance;
    }

    public int getID() {
        return ID;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }

    public double getRepository() {
        return repository;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public void setRepository(double repository) {
        this.repository = repository;
    }

    public synchronized void withdrawMoney(double amount) {
        if (amount > balance || amount <= 0) {
            System.out.println("Error!");
        } else {
            balance -= amount;
            bankBalance -= amount;
        }
    }

    public synchronized void addMoney(double amount) {
        if (amount <= 0) {
            System.out.println("Error!");
        } else {
            balance += amount;
            bankBalance -= amount;
        }
    }

    public synchronized void moneyTransaction(double amount, Customer to) {
        if (this.balance >= amount) {
            to.balance += amount;
            this.balance -= amount;
            bankBalance -= amount;
        } else {
            System.out.println("Error!");
        }
    }

    public synchronized void payment(double serviceCost) {
        if (serviceCost > balance || serviceCost <= 0) {
            System.out.println("Error!");
        } else {
            balance -= serviceCost;
        }
    }

    public synchronized CharSequence bankRepository(Bank customer) {
        double lambda;
        if (bankBalance <= 0 || bankBalance < customer.getBalance()) {
            bankBalance += (500 + customer.getAmount());
            repository -= (500 + customer.getAmount());
        } else if (bankBalance > 1500) {
            lambda = balance - 1500;
            repository += lambda;
        }
        return "ATM Balance: " + getBankBalance() + " Repository Balance: " + getRepository();
    }

    public synchronized double convert(double value, String str) {
        switch (str) {
            case "USD":
                value *= 2.55;
                break;
            case "EUR":
                value *= 2.87;
                break;
            default:
                break;
        }
        return (double) Math.round(value * 100) / 100;
    }

    @Override
    public String toString() {
        String currentThreadName = Thread.currentThread().getName();
        return "Thread name: " + currentThreadName
                + "\nID: " + getID()
                + " | First Name: " + getFirstName()
                + " | Second Name: " + getSecondName()
                + " | Account Number: " + getAccountNumber()
                + " | Balance: " + getBalance()
                + " | Bank Balance: " + getBankBalance()
                + "\n";
    }
}
