package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Cart;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.service.BookstoreService;

@Controller
@RequestMapping("/cart")
@SessionAttributes("order")
public class CartController {

    @Autowired
    private Cart cart;

    @Autowired
    private BookstoreService bookstoreService;

    @RequestMapping("/add/{bookId}")
    public String addToCart(@PathVariable("bookId") long bookId, @RequestHeader("referer") String referer) {
        Book book = this.bookstoreService.findBook(bookId);
        this.cart.addBook(book);
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    @ModelAttribute("order")
    public Order checkout(HttpServletRequest request) {
        Account customer = (Account) WebUtils.getRequiredSessionAttribute(request, "customer");
        Order order = this.bookstoreService.createOrder(this.cart, customer);
        return order;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkout(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult,
            SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "cart/checkout";
        } else {
            this.bookstoreService.store(order);
            sessionStatus.setComplete();
            return "redirect:/customer/account";
        }
    }

}
