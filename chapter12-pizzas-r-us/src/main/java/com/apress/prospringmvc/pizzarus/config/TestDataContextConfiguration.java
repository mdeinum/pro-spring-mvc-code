package com.apress.prospringmvc.pizzarus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.pizzarus.support.InitialDataSetup;
import com.apress.prospringmvc.pizzarus.web.config.PizzaRUsWebApplicationInitializer;

/**
 * Trigger that will call {@link InitialDataSetup#setupData()} to loads initial data into the database. This gets the
 * test-data profile, when we start the application in the container we also load this profile. In other environments
 * you probably want to load this profile conditionally depending on the environment.
 * 
 * @see PizzaRUsWebApplicationInitializer
 * 
 * @author Koen Serneels
 */

@Configuration
public class TestDataContextConfiguration {

	@Bean(initMethod = "setupData")
	public InitialDataSetup setupData() {
		return new InitialDataSetup();
	}
}
