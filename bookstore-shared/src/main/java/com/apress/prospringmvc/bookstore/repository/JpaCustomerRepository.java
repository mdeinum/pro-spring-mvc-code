package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.bookstore.domain.Customer;

@Repository("customerRepository")
public class JpaCustomerRepository implements CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Customer findByUsername(String username) {
		String hql = "select c from Customer c where c.username=:username";
		TypedQuery<Customer> query = this.entityManager.createQuery(hql, Customer.class).setParameter("username",
				username);
		List<Customer> customers = query.getResultList();

		return customers.size() == 1 ? customers.get(0) : null;
	}

	@Override
	public Customer findById(long id) {
		return this.entityManager.find(Customer.class, id);
	}

	@Override
	public Customer save(Customer customer) {
		if (customer.getId() > 0) {
			return this.entityManager.merge(customer);
		} else {
			this.entityManager.persist(customer);
			return customer;
		}
	}

}
