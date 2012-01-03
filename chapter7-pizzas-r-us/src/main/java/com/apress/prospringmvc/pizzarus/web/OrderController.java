package com.apress.prospringmvc.pizzarus.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.service.OrderService;
import com.apress.prospringmvc.pizzarus.service.PizzaService;

@Controller
@RequestMapping(value = "/order.htm")
@SessionAttributes("newOrder")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private PizzaService pizzaService;

    @ModelAttribute
    public void getPizzas(final Model model) {
        model.addAttribute(this.pizzaService.findAll());
    }

    @ModelAttribute("newOrder")
    public Order newOrder() {
        return new Order();
    }

    @RequestMapping(method = RequestMethod.GET)
    public void index() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public String newOrder(final @ModelAttribute Order newOrder, final BindingResult result,
            final SessionStatus sessionStatus) {
        if (result.hasErrors()) {
            //handle error
            return "order";
        } else {
            this.orderService.newOrder(newOrder);
            this.logger.info("Received an order: {}", newOrder);
            sessionStatus.setComplete();
            return "redirect:/order-success.htm";
        }
    }

}
