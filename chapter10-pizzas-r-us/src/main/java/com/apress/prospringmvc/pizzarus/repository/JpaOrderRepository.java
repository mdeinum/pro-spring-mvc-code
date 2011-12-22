package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;

@Repository("orderRepository")
public class JpaOrderRepository implements OrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long saveOrder(Customer customer, Order order) {
		customer = entityManager.find(Customer.class, customer.getId());
		order.setCustomer(customer);
		order = entityManager.merge(order);
		return order.getId();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getOrders(Long customerId) {
		return entityManager.createQuery("select o from Order o left join o.customer c where c.id = :customerId")
				.setParameter("customerId", customerId).getResultList();
	}
}
