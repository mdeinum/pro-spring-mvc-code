package com.apress.prospringmvc.pizzarus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.pizzarus.support.InitialDataSetup;

@Configuration
// @Profile("test")
public class TestDataContextConfiguration {

	@Bean(initMethod = "setupData")
	public InitialDataSetup setupData() {
		return new InitialDataSetup();
	}
}
