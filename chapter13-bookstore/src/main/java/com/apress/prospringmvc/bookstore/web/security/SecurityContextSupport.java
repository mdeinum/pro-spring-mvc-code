package com.apress.prospringmvc.bookstore.web.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextSupport {

	public static BookstoreUserDetails getUserDetails() {
		return (BookstoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
