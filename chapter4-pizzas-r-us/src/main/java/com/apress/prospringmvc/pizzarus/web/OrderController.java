package com.apress.prospringmvc.pizzarus.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.service.OrderService;

@Controller
@RequestMapping(value = "/order.htm")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order.htm", method = RequestMethod.GET)
    public void index() {
    }

    @RequestMapping(value = "/order.htm", method = RequestMethod.POST)
    public String newOrder(final @RequestParam(required = true) String name, final @RequestParam String address,
            final @RequestParam String postcode, final @RequestParam String city, final @RequestParam String country,
            final @RequestParam String creditcard, final @RequestParam String comment,
            final RedirectAttributes redirectAttributes) {
        Order newOrder = new Order();
        newOrder.setName(name);
        newOrder.setAddress(address);
        newOrder.setPostcode(postcode);
        newOrder.setCity(city);
        newOrder.setCountry(country);
        newOrder.setCreditcard(creditcard);
        newOrder.setComment(comment);

        this.orderService.newOrder(newOrder);

        this.logger.info("Received an order: {}", newOrder);

        redirectAttributes.addAttribute("orderNumber", newOrder.getId());

        return "redirect:order-success.htm";
    }

}
