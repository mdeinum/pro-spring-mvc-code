package com.apress.prospringmvc.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

/**
 * {@link HandlerInterceptor} to apply security to controllers.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

    public static final String REQUESTED_URL = "REQUESTED_URL";
    public static final String ACCOUNT_ATTRIBUTE = "account";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ACCOUNT_ATTRIBUTE);
        if (account == null) {

            //Retrieve and store the original URL.
            String url = request.getRequestURL().toString();
            WebUtils.setSessionAttribute(request, REQUESTED_URL, url);
            throw new AuthenticationException("Authentication required.", "authentication.required");
        }
        return true;
    }

}
