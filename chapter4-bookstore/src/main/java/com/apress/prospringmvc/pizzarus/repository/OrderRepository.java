package com.apress.prospringmvc.pizzarus.repository;

import com.apress.prospringmvc.pizzarus.domain.Order;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 15-12-11
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public interface OrderRepository {

    void save(Order order);
}
