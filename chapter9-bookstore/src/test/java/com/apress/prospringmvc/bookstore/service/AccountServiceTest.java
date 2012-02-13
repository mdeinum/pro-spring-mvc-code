package com.apress.prospringmvc.bookstore.service;

import static junit.framework.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.test.util.ReflectionTestUtils;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.support.AccountBuilder;
import com.apress.prospringmvc.bookstore.repository.AccountRepository;

public class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;

    @Before
    public void setup() {
        accountService = new AccountServiceImpl();

        Account account = new AccountBuilder().address("Herve", "4650", "Rue de la station", "1", null, "Belgium")
                .credentials("john", "secret").name("John", "Doe").build(true);

        accountRepository = Mockito.mock(AccountRepository.class);
        Mockito.when(accountRepository.findByUsername(Mockito.anyString())).thenReturn(account);

        ReflectionTestUtils.setField(accountService, "accountRepository", accountRepository);
    }

    @After
    public void verify() {
        Mockito.verify(accountRepository, VerificationModeFactory.times(1)).findByUsername(Mockito.anyString());
    }

    @Test(expected = AuthenticationException.class)
    public void testLoginFailure() throws AuthenticationException {
        accountService.login("john", "fail");
    }

    @Test()
    public void testLoginSuccess() throws AuthenticationException {
        Account account = accountService.login("john", "secret");
        assertEquals("John", account.getFirstName());
        assertEquals("Doe", account.getLastName());
    }
}
