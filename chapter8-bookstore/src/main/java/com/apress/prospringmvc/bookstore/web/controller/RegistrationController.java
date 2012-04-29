package com.apress.prospringmvc.bookstore.web.controller;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.validation.AccountValidator;

/**
 * Controller to handle the registration of a new {@code Account}
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Controller
@RequestMapping("/customer/register")
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @ModelAttribute("countries")
    public Map<String, String> countries(Locale currentLocale) {
        Map<String, String> countries = new TreeMap<String, String>();
        for (Locale locale : Locale.getAvailableLocales()) {
            countries.put(locale.getCountry(), locale.getDisplayCountry(currentLocale));
        }
        return countries;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        binder.setValidator(new AccountValidator());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute
    public Account register(Locale currentLocale) {
        Account account = new Account();
        account.getAddress().setCountry(currentLocale.getCountry());
        return account;
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String handleRegistration(@Valid @ModelAttribute Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/register";
        }
        this.accountService.save(account);
        return "redirect:/customer/account/" + account.getId();
    }
}
