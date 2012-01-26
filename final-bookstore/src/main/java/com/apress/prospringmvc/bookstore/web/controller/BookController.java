package com.apress.prospringmvc.bookstore.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;

import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

@Controller
@RequestMapping("/book")
public class BookController implements ResourceLoaderAware {

    @Autowired
    private BookstoreService bookstoreService;

    @Autowired
    private CategoryService categoryService;

    private ResourceLoader resourceLoader;

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

    @RequestMapping(value = "{isbn}/image", method = RequestMethod.GET)
    public void image(@PathVariable("isbn") String isbn, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Resource resource = this.resourceLoader.getResource("classpath:/META-INF/web-resources/images/books/" + isbn
                + "/book_front_cover.png");
        if (resource.exists()) {
            if (new ServletWebRequest(request, response).checkNotModified(resource.lastModified())) {
                return;
            }
            FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
