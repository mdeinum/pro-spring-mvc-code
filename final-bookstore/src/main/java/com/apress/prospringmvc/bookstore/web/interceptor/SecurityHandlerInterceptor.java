package com.apress.prospringmvc.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

    public static final String REQUESTED_URL = "REQUESTED_URL";
    public static final String REQUESTED_PARAMS = "REQUESTED_PARAMS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, "account");
        if (account == null) {

            //Retrieve and store the original URL.
            String url = request.getRequestURL().toString();
            WebUtils.setSessionAttribute(request, REQUESTED_URL, url);

            throw new AuthenticationException("Authentication required.", "authentication.required");
        }
        return true;
    }

}
