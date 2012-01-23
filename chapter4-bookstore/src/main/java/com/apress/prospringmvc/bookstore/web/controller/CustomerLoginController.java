package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.service.AccountService;

@Controller
@RequestMapping("/customer/login")
public class CustomerLoginController {

    private static final String ACCOUNT_SESSION_ATTRIBUTE = "account";

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public void handleLogin(@RequestParam String username, @RequestParam String password, HttpSession session)
            throws AuthenticationException {
        Account account = this.accountService.login(username, password);
        session.setAttribute(ACCOUNT_SESSION_ATTRIBUTE, account); //store account in the session
    }

}
