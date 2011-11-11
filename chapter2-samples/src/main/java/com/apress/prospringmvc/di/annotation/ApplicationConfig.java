package com.apress.prospringmvc.di.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.AccountService;
import com.apress.prospringmvc.TransferService;
import com.apress.prospringmvc.di.AccountServiceImpl;

@Configuration
public class ApplicationConfig {

	@Bean
	public AccountService accountService() {
		return new AccountServiceImpl();
	}
	
	@Bean
	public TransferService transferService() {
		return new TransferServiceImpl();
	}
	
}
