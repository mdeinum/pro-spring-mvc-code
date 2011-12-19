package com.apress.prospringmvc.pizzarus.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.service.OrderService;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 15-12-11
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order.htm", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("order");
    }

    @RequestMapping(value = "/order.htm", method = RequestMethod.POST)
    public ModelAndView newOrder(final HttpServletRequest request) {

        Order newOrder = new Order();
        newOrder.setName(request.getParameter("name"));
        newOrder.setAddress(request.getParameter("address"));
        newOrder.setPostcode(request.getParameter("postcode"));
        newOrder.setCity(request.getParameter("city"));
        newOrder.setCountry(request.getParameter("country"));
        newOrder.setCreditcard(request.getParameter("creditcard"));
        newOrder.setComment(request.getParameter("comment"));

        this.orderService.newOrder(newOrder);

        this.logger.info("Received an order: {}", newOrder);

        return new ModelAndView("order");
    }

}
