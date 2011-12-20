package com.apress.prospringmvc.pizzarus.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(final Order order) {
        this.sessionFactory.getCurrentSession().save(order);
    }
}
