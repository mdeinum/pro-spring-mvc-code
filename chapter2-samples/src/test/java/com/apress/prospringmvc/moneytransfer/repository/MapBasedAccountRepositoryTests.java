package com.apress.prospringmvc.moneytransfer.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.apress.prospringmvc.moneytransfer.domain.Account;

public class MapBasedAccountRepositoryTests {

    private final MapBasedAccountRepository repository = new MapBasedAccountRepository();

    @Before
    public void setup() {
        //Need to simulate calling of @PostContruct annotated method.
        this.repository.initialize();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonExistingAccount() {
        this.repository.find("foobar");
    }

    @Test
    public void existingAccount() {
        Account account = this.repository.find("123456");
        assertNotNull(account);
        assertEquals("123456", account.getNumber());
    }

    @Test
    public void storeAndFind() {
        Account account = new Account("foobar");
        account.setOwner("F. BarBaz");
        this.repository.store(account);

        assertEquals(account, this.repository.find("foobar"));
    }

}
