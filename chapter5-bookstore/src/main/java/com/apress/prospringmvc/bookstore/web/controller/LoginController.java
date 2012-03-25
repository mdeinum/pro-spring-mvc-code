package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

/**
 * Controller for handling login and logout.
 * 
 * @author marten
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    public static final String ACCOUNT_ATTRIBUTE = "account";

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request)
            throws AuthenticationException {
        try {
            Account account = this.accountService.login(username, password);
            WebUtils.setSessionAttribute(request, ACCOUNT_ATTRIBUTE, account);
            return "redirect:/index.htm";
        } catch (AuthenticationException ae) {
            request.setAttribute("exception", ae);
            return "login";
        }
    }

}
