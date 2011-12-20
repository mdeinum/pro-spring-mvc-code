package com.apress.prospringmvc.pizzarus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.pizzarus.support.DataSetup;

@Configuration
//@Profile("test")
public class TestDataContextConfiguration {

	@Bean(initMethod = "setupData")
	public DataSetup setupData() {
		return new DataSetup();
	}
}
