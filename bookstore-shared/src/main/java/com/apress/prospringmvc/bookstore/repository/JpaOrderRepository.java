package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;

/**
 * Jpa based {@link OrderRepository} implementation.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Repository("orderRepository")
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findById(long id) {
        return this.entityManager.find(Order.class, id);
    }

    @Override
    public Order save(Order order) {
        this.entityManager.persist(order);
        return order;
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        final String hql = "select o from Order o where o.customer=:customer";
        TypedQuery<Order> query = this.entityManager.createQuery(hql, Order.class);
        query.setParameter("customer", customer);
        return query.getResultList();
    }

}
