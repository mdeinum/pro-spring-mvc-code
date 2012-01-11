package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.service.CustomerService;

@Controller
@RequestMapping("/customer/register")
public class CustomerRegistrationController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute
    public Customer register() {
        return new Customer();
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    @ModelAttribute
    public String handleRegistration(@ModelAttribute Customer customer, BindingResult result) {
        this.customerService.save(customer);
        return "redirect:/customer/account";
    }

    public String account(HttpSession session) {
        Customer customer = null;
        if (session != null && session.getAttribute("customer") != null) {
            customer = (Customer) session.getAttribute("customer");
        }
        if (customer == null) {
            return "redirect:/customer/login";
        }

        return "account";
    }

    @ExceptionHandler
    public void authenticationFailure(AuthenticationException ex) {
    }

}
