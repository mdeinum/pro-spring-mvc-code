package com.apress.prospringmvc.pizzarus.web;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.service.OrderService;
import com.apress.prospringmvc.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping(value = "/order.htm")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final Random random = new Random(12345L);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order.htm", method = RequestMethod.GET)
    public @SessionAttribute(value = "rand")
    Long index() {
        Long rand = this.random.nextLong();
        this.logger.info("Created random number: {}", rand);
        return rand;
    }

    @RequestMapping(value = "/order.htm", method = RequestMethod.POST)
    public String newOrder(final Order newOrder, @SessionAttribute final Long rand) {
        this.orderService.newOrder(newOrder);
        this.logger.info("Received an order: {}", newOrder);
        this.logger.info("Received random number: {}", rand);
        return "redirect:/order-success.htm";
    }

}
