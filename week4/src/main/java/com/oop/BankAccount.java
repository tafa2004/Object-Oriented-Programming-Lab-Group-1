package com.oop;

public class BankAccount {

    public String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, double balance) {

        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Deposit money
    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        this.balance = this.balance + amount;
    }

    // Withdraw money
    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        this.balance = this.balance - amount;
    }

    // Getter
    public double getBalance() {
        return this.balance;
    }

    // Setter
    public void setBalance(double balance) {

        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.balance = balance;
    }
}