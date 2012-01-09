package com.apress.prospringmvc.pizzarus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apress.prospringmvc.pizzarus.service.PizzasService;

@Controller
public class PizzasController {

	@Autowired
	private PizzasService pizzasService;
	
	@RequestMapping(value="/pizzas.htm")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("pizzas");
		mav.addObject("pizzas", pizzasService.getPizzas());
		return mav;
	}
	
}
