package com.apress.prospringmvc.pizzarus.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes=InfrastructureConfig.class)
public class ContextLoadingTests {
	
	private Logger logger = LoggerFactory.getLogger(ContextLoadingTests.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void contextLoading() {
		logger.info("DataSource: {}", dataSource.getClass());
		assertNotNull(dataSource);
		logger.info("TransactionManager: {}", transactionManager.getClass());		
		assertNotNull(transactionManager);
		logger.info("JdbcTemplate: {}", jdbcTemplate);
		assertNotNull(jdbcTemplate);
	}
	
	@Test
	public void testInit() {
		int count = jdbcTemplate.queryForInt("select count(*) from pizza");
		assertEquals(2, count);
	}

}
