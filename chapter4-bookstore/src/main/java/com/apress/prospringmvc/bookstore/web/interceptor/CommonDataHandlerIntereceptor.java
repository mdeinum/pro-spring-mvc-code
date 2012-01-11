package com.apress.prospringmvc.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.service.BookService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

public class CommonDataHandlerIntereceptor extends HandlerInterceptorAdapter {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            modelAndView.addObject("randomBooks", this.bookService.findRandomBooks());
            modelAndView.addObject("categories", this.categoryService.findAll());
            modelAndView.addObject("customer", WebUtils.getSessionAttribute(request, "customer"));
        }

    }

}
