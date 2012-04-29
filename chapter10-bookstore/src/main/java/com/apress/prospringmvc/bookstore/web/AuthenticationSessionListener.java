package com.apress.prospringmvc.bookstore.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.apress.prospringmvc.bookstore.service.AccountService;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;

/**
 * JEE listener which will automatically authenticate a user for each new HTTP session that is started.
 * This is implemented in the first SWF chapter to keep the contents as simply as possible and mainly
 * focus on the the SWF contents. In the next chapter(s) this listener will become obsolete and replace
 * by a home grown security mechanism, and in chapter 13 finally replaced by Spring Security.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *  
 */
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
