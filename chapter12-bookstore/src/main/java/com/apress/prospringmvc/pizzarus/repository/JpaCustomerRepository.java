package com.apress.prospringmvc.pizzarus.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Customer;

/**
 * JPA implementation of the {@link CustomerRepository}
 * 
 * @author Koen Serneels
 */

@Repository("customerRepository")
public class JpaCustomerRepository implements CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @see CustomerRepository#getCustomer(String)
	 */
	@Override
	public Customer getCustomer(String username) {
		return (Customer) entityManager.createQuery("select c from Customer c where c.username = :username")
				.setParameter("username", username).getSingleResult();
	}
}
