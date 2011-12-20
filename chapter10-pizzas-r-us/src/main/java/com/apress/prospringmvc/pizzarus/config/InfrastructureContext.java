package com.apress.prospringmvc.pizzarus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class InfrastructureContext {

	/**
	 * DataSource configuration used for testing.
	 * 
	 * @author M. Deinum
	 */
	@Configuration
	@Profile("test")
	private static class TestDataContext {

		@Bean(initMethod = "setupData")
		public DataSetup setupData() {
			return new DataSetup();
		}

	}
}
