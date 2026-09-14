package com.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {
    @Test
    void testInitialBalance() {
        BankAccount account = new BankAccount();

        assertEquals(0.0, account.balance);
    }
}