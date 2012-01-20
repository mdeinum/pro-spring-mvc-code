package com.apress.prospringmvc.bookstore.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.apress.prospringmvc.bookstore.service.CustomerService;

@Component
public class BookstoreUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(grantedAuthority);
		return new BookstoreUserDetails(customerService.getCustomer(username), grantedAuthorities);
	}
}
