package com.apress.prospringmvc.bookstore.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.service.CustomerService;

/**
 * Controller to handle the registration of a new {@link Customer}
 * @author marten
 *
 */
@Controller
@RequestMapping("/customer/register")
public class RegistrationController extends AbstractCustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute
    public Customer register(Locale currentLocale) {
        Customer customer = new Customer();
        customer.getAddress().setCountry(currentLocale.getCountry());
        return customer;
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String handleRegistration(@ModelAttribute Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/register";
        }
        this.customerService.save(customer);
        return "redirect:/customer/account";
    }
}
