package com.apress.prospringmvc.bookstore.web.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Cart;
import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.OrderDetail;
import com.apress.prospringmvc.bookstore.service.BookstoreService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private Cart cart;

    @Autowired
    private BookstoreService bookstoreService;

    @RequestMapping("/add/{bookId}")
    public String addToCart(@PathVariable("bookId") long bookId, @RequestHeader("referer") String referer) {
        Book book = this.bookstoreService.findById(bookId);
        this.cart.addBook(book);
        return "redirect:" + referer;
    }

    @RequestMapping("/checkout")
    public void checkout(HttpServletRequest request, Model model) {
        Account account = (Account) WebUtils.getRequiredSessionAttribute(request, "account");
        Order order = new Order(account);
        for (Entry<Book, Integer> line : this.cart.getBooks().entrySet()) {
            OrderDetail detail = new OrderDetail();
            order.addOrderDetail(new OrderDetail(line.getKey(), line.getValue()));
        }
        this.cart.clear();
        model.addAttribute("order", order);
    }
}
