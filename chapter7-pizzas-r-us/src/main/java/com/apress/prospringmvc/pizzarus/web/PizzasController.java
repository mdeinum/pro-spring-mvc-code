package com.apress.prospringmvc.pizzarus.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.service.PizzaService;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PizzasController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/pizzas.htm")
    public List<Pizza> menu() {
        return this.pizzaService.findAll();
    }
}
