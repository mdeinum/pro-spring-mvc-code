package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.service.CustomerService;

@Controller
@RequestMapping("/customer/login")
public class CustomerLoginController {

    private static final String CUSTOMER_SESSION_ATTRIBUTE = "customer";

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public void handleLogin(@RequestParam String username, @RequestParam String password, HttpSession session)
            throws AuthenticationException {
        Customer customer = this.customerService.login(username, password);
        session.setAttribute(CUSTOMER_SESSION_ATTRIBUTE, customer); //store customer in the session
    }

}
