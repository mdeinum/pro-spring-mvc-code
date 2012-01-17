package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookstoreService bookstoreService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("searchCriteria")
    public BookSearchCriteria searchCriteria() {
        return new BookSearchCriteria();
    }

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String list(@ModelAttribute BookSearchCriteria searchCriteria, ModelMap model) {
        model.addAttribute(this.bookstoreService.findBooks(searchCriteria));
        model.addAttribute("categories", this.categoryService.findAll());
        return "book/search";
    }

    @RequestMapping(value = "{bookId}/image", method = RequestMethod.GET)
    public void image(@PathVariable("bookId") long bookId, HttpServletResponse response) {
    }

}
