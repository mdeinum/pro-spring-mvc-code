package com.apress.prospringmvc.pizzarus.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/order-success.htm")
public class OrderSuccessController {

    @RequestMapping(method = RequestMethod.GET)
    public void orderSuccess(@RequestParam(required = true) final String orderNumber, final Model model) {
        model.addAttribute("orderNumber", orderNumber);
    }

}
