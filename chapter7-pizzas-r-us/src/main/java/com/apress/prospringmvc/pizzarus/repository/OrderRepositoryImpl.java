package com.apress.prospringmvc.pizzarus.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Order;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 15-12-11
 * Time: 16:08
 * To change this template use File | Settings | File Te    mplates.
 */
@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(final Order order) {
        this.entityManager.persist(order);
    }
}
