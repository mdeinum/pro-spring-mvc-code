package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.web.interceptor.SecurityHandlerInterceptor;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request)
            throws AuthenticationException {
        Account account = this.accountService.login(username, password);
        WebUtils.setSessionAttribute(request, SecurityHandlerInterceptor.ACCOUNT_ATTRIBUTE, account);
        String url = (String) WebUtils.getSessionAttribute(request, SecurityHandlerInterceptor.REQUESTED_URL);
        WebUtils.setSessionAttribute(request, SecurityHandlerInterceptor.REQUESTED_URL, null); // Remove the attribute
        if (StringUtils.hasText(url) && !url.contains("login")) { // Prevent loops for the login page.
            return "redirect:" + url;
        } else {
            return "/index.htm";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        WebUtils.setSessionAttribute(request, "account", null);
        return "redirect:/index.htm";
    }

}
