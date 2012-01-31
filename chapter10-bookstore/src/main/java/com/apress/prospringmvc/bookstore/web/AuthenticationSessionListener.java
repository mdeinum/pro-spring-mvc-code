package com.apress.prospringmvc.bookstore.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

@WebListener
public class AuthenticationSessionListener implements HttpSessionListener {

	public static final String AUTHENTICATED_ACCOUNT_KEY = "authenticatedAccount";

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		AccountService accountService = WebApplicationContextUtils.getWebApplicationContext(
				httpSessionEvent.getSession().getServletContext()).getBean(AccountService.class);

		try {
			httpSessionEvent.getSession().setAttribute(AUTHENTICATED_ACCOUNT_KEY, accountService.login("jd", "secret"));
		} catch (AuthenticationException authenticationException) {
			throw new RuntimeException(authenticationException);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// Do nothing
	}
}
