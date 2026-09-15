package com.oop;

public class Main {

    public static void main(String[] args) {

        try {
            BankAccount jacobsAccount = new BankAccount("Jacob", 500);

            System.out.println("Account holder: " + jacobsAccount.accountHolder);
            System.out.println("Starting balance: " + jacobsAccount.getBalance());

            jacobsAccount.deposit(200);
            System.out.println("After deposit: " + jacobsAccount.getBalance());

            jacobsAccount.withdraw(100);
            System.out.println("After withdrawal: " + jacobsAccount.getBalance());

            // Test invalid withdrawal
            jacobsAccount.withdraw(1000);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}