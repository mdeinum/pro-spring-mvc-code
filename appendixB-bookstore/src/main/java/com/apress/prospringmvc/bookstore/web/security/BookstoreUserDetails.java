package com.apress.prospringmvc.bookstore.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apress.prospringmvc.bookstore.domain.Customer;

public class BookstoreUserDetails implements UserDetails {

	private Customer customer;
	private List<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	public BookstoreUserDetails(Customer customer, List<? extends GrantedAuthority> authorities) {
		this.customer = customer;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		return customer.getUsername();
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
