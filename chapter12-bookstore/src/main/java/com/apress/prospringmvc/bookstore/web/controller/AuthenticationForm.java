package com.apress.prospringmvc.bookstore.web.controller;

import java.io.Serializable;

/**
 * Simple form for holding username and password
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */

public class AuthenticationForm implements Serializable {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
