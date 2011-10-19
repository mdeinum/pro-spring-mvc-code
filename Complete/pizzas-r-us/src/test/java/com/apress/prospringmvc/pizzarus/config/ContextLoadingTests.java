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
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;


@ActiveProfiles("test")
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes=InfrastructureConfig.class)
public class ContextLoadingTests extends AbstractTransactionalJUnit4SpringContextTests {
	
	private Logger logger = LoggerFactory.getLogger(ContextLoadingTests.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Test
	public void contextLoading() {
		logger.info("DataSource: {}", dataSource.getClass());
		assertNotNull(dataSource);
		logger.info("TransactionManager: {}", transactionManager.getClass());		
		assertNotNull(transactionManager);
	}
	
	@Test
	public void testInit() {
		int count = countRowsInTable("pizza"); 
		assertEquals(2, count);
	}

}
