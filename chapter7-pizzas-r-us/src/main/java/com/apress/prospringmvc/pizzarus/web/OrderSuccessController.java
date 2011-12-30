package com.apress.prospringmvc.pizzarus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/order-success.htm")
public class OrderSuccessController {

    @RequestMapping(method = RequestMethod.GET)
    public void orderSuccess() {
    }

}
