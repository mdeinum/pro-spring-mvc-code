package com.apress.prospringmvc.bookstore.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

@Component
@WebListener
public class AuthenticationSessionListener implements HttpSessionListener {

	public static final String AUTHENTICATED_ACCOUNT_KEY = "authenticatedAccount";

	@Autowired
	private AccountService accountService;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		try {
			se.getSession().setAttribute(AUTHENTICATED_ACCOUNT_KEY, accountService.login("jd", "secret"));
		} catch (AuthenticationException authenticationException) {
			throw new RuntimeException(authenticationException);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// Do nothing
	}
}
