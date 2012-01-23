package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.service.AccountService;

@Controller
@RequestMapping("/customer/register")
public class CustomerRegistrationController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute
    public Account register() {
        return new Account();
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    @ModelAttribute
    public String handleRegistration(@ModelAttribute Account account, BindingResult result) {
        this.accountService.save(account);
        return "redirect:/customer/account";
    }

    public String account(HttpSession session) {
        Account account = null;
        if (session != null && session.getAttribute("account") != null) {
            account = (Account) session.getAttribute("account");
        }
        if (account == null) {
            return "redirect:/customer/login";
        }

        return "account";
    }

    @ExceptionHandler
    public void authenticationFailure(AuthenticationException ex) {
    }

}
