package com.apress.prospringmvc.pizzarus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.pizzarus.repository.CustomerRepository;
import com.apress.prospringmvc.pizzarus.repository.JpaCustomerRepository;
import com.apress.prospringmvc.pizzarus.repository.JpaPizzaRepository;
import com.apress.prospringmvc.pizzarus.repository.PizzaRepository;

@Configuration
public class RepositoryConfiguration {

	@Bean
	public CustomerRepository customerRepository() {
		return new JpaCustomerRepository();
	}

	@Bean
	public PizzaRepository pizzaRepository() {
		return new JpaPizzaRepository();
	}

}
