package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.repository.AccountRepository;
import com.apress.prospringmvc.bookstore.repository.OrderRepository;

@Controller
@RequestMapping("/customer/account")
public class AccountController extends AbstractCustomerController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @ModelAttribute
    public Account formObject(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        return this.accountRepository.findById(account.getId());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("orders", this.orderRepository.findByAccount(account));
        return "customer/account";
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String update(@ModelAttribute Account account, HttpSession session) {
        this.accountRepository.save(account);
        session.setAttribute("account", account); //Update account in session
        return "redirect:/customer/account";
    }

}
