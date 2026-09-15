package com.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MemoryDemoTest {

    @Test
    void separateObjectsDoNotShareState() {
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount();

        acc1.balance = 500;

        assertEquals(0, acc2.balance);
    }

    @Test
    void aliasedObjectsShareState() {
        BankAccount acc1 = new BankAccount();
        acc1.balance = 500;

        BankAccount acc2 = acc1;
        acc2.balance = 750;

        assertEquals(750, acc1.balance);
    }
}
