package com.apress.prospringmvc.pizzarus.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

//@Configuration
public class InfrastructureContext {

	@Autowired
	private DataSource dataSource;

	
	/**
	 * DataSource configuration used for testing.
	 * 
	 * @author M. Deinum
	 */
//	@Configuration
	@Profile("test")
	private static class TestDataContext {
		
		@Bean
		public DataSource dataSource() {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			builder.setType(EmbeddedDatabaseType.H2);
			builder.addScript("classpath:/sql/schema.sql").addScript("classpath:/sql/data.sql");
			return builder.build();
		}
		
	}

	/**
	 * DataSource configuration used for the local environment.
	 * 
	 * @author M. Deinum
	 *
	 */
//	@Configuration
	@Profile("local")
	private static class LocalDataContext {
		
		@Bean
		public DataSource dataSource() {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			builder.setType(EmbeddedDatabaseType.H2);
			builder.setName("pizzas");
			builder.addScript("classpath:/sql/schema.sql").addScript("classpath:/sql/data.sql");
			return builder.build();
		}		
	}
	
}
