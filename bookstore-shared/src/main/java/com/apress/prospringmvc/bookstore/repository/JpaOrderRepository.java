package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;

@Repository("orderRepository")
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order save(Order order) {
        if (order.getId() > 0) {
            return this.entityManager.merge(order);
        } else {
            this.entityManager.persist(order);
            return order;
        }
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        String hql = "select o from Order o where o.customer=:customer";
        TypedQuery<Order> query = this.entityManager.createQuery(hql, Order.class);
        query.setParameter("customer", customer);
        return query.getResultList();
    }

}
