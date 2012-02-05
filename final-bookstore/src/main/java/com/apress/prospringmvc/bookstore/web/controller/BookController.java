package com.apress.prospringmvc.bookstore.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookstoreService bookstoreService;

    @Autowired
    private CategoryService categoryService;

    private ResourceLoader resourceLoader;

    /**
     * 
     * @return the model object
     */
    @ModelAttribute
    public BookSearchCriteria criteria() {
        return new BookSearchCriteria();
    }

    @ModelAttribute("categories")
    public Collection<Category> categories() {
        return this.categoryService.findAll();
    }

    @RequestMapping(value="/search", method = { RequestMethod.GET })
    public Collection<Book> list(@ModelAttribute BookSearchCriteria criteria, Model model) {
        return this.bookstoreService.findBooks(criteria));
    }
}
