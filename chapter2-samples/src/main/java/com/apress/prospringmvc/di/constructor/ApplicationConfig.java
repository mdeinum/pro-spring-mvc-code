package com.apress.prospringmvc.di.constructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.AccountService;
import com.apress.prospringmvc.TransferService;
import com.apress.prospringmvc.di.AccountServiceImpl;

/**
 * Constructor based dependency injection sample.
 * 
 * @author M. Deinum
 */
@Configuration
public class ApplicationConfig {

	@Bean
	public AccountService accountService() {
		return new AccountServiceImpl();
	}
	
	@Bean
	public TransferService transferService() {
		return new TransferServiceImpl(accountService());
	}
	
}
