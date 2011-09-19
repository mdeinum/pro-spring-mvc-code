package com.apress.prospringmvc.pizzarus.config;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class InfrastructureConfig {

	@Autowired
	private DataSource dataSource;
	
	
	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
		return txManager;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	@Configuration
	@Profile("test")
	static class LocalDataSourceConfig {

		@Bean
		public DataSource dataSource() {
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:/sql/schema.sql").addScript("classpath:/sql/test-data.sql").build();
		}
		
	}

	@Configuration
	@Profile("default")
	static class DataSourceConfig {
		
		@Autowired
		private Environment environment;

		@Bean
		public DataSource dataSource() throws SQLException {
			SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
			String url = environment.getProperty("jdbc.url");
			dataSource.setDriver(DriverManager.getDriver(url));
			dataSource.setUrl(url);
			dataSource.setUsername(environment.getProperty("jdbc.username"));
			dataSource.setPassword(environment.getProperty("jdbc.password"));
			return dataSource;
		}
		
	}

	
}
