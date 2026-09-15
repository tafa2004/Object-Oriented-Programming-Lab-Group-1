package com.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    void testDeposit() {

        BankAccount account = new BankAccount("Jacob", 500);

        account.deposit(200);

        assertEquals(700, account.getBalance());
    }

    @Test
    void testWithdrawal() {

        BankAccount account = new BankAccount("Jacob", 500);

        account.withdraw(100);

        assertEquals(400, account.getBalance());
    }

    @Test
    void testNegativeDepositIsRejected() {

        BankAccount account = new BankAccount("Jacob", 500);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(-100)
        );
    }

    @Test
    void testInsufficientFundsIsRejected() {

        BankAccount account = new BankAccount("Jacob", 500);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(1000)
        );
    }
}