package com.apress.prospringmvc.pizzarus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.repository.OrderRepository;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 15-12-11
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void newOrder(final Order order) {
        this.orderRepository.save(order);
    }
}
