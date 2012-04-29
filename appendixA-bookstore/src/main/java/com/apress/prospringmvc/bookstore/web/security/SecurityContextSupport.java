package com.apress.prospringmvc.bookstore.web.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Support class for easy access to our custom {@link BookstoreUserDetails}
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
public class SecurityContextSupport {

	public static BookstoreUserDetails getUserDetails() {
		return (BookstoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
