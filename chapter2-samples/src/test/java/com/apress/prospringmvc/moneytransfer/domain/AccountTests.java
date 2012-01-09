package com.apress.prospringmvc.moneytransfer.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountTests {

    private static final BigDecimal THOUSAND = new BigDecimal("1000.00");
    private static final BigDecimal HUNDRED = new BigDecimal("100.00");

    @Test
    public void creation() {
        Account account = new Account("123456");
        assertEquals("123456", account.getNumber());
        assertEquals(BigDecimal.ZERO, account.getBalance());
        assertNull(account.getOwner());

        account.setOwner("test");
        assertEquals("test", account.getOwner());
    }

    @Test
    public void debit() {
        Account account = new Account("123456");
        account.debit(THOUSAND);

        assertEquals(THOUSAND, account.getBalance());

        account.debit(HUNDRED);

        assertEquals(THOUSAND.add(HUNDRED), account.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creditInsufficientFunds() {
        Account account = new Account("123456");
        account.credit(THOUSAND);

    }

    @Test
    public void credit() {
        Account account = new Account("123456");
        account.debit(THOUSAND);

        assertEquals(THOUSAND, account.getBalance());

        account.credit(HUNDRED);
        assertEquals(THOUSAND.subtract(HUNDRED), account.getBalance());

    }

}
