package com.apress.prospringmvc.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Customer customer = (Customer) WebUtils.getSessionAttribute(request, "customer");
        if (customer == null) {
            throw new AuthenticationException("Authentication required.", "authentication.required");
        }
        return true;
    }

}
