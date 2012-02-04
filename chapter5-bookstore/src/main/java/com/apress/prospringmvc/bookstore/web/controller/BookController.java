package com.apress.prospringmvc.bookstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.service.BookstoreService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookstoreService bookstoreService;

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Model model, @RequestParam(required = false) String title) {
        BookSearchCriteria criteria = new BookSearchCriteria();
        criteria.setTitle(title);
        model.addAttribute(this.bookstoreService.findBooks(criteria));
        return "book/search";

    }

}
