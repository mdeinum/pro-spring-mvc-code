package com.apress.prospringmvc.pizzarus.service;

import com.apress.prospringmvc.pizzarus.domain.Order;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 15-12-11
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public interface OrderService {

    public void newOrder(Order order);
}
