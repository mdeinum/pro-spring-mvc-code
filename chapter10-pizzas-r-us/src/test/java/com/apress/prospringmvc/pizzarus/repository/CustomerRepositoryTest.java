package com.apress.prospringmvc.pizzarus.repository;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.prospringmvc.pizzarus.config.InfrastructureContextConfiguration;
import com.apress.prospringmvc.pizzarus.config.RepositoryConfiguration;
import com.apress.prospringmvc.pizzarus.config.TestDataContextConfiguration;
import com.apress.prospringmvc.pizzarus.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InfrastructureContextConfiguration.class, RepositoryConfiguration.class,
		TestDataContextConfiguration.class })
@ActiveProfiles("test")
public class CustomerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void testGetCustomer() {
		Customer customer = customerRepository.getCustomer("jd");
		Assert.assertNotNull(customer);
	}

}
