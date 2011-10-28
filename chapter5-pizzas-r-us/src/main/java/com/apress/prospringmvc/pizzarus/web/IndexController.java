package com.apress.prospringmvc.pizzarus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apress.prospringmvc.pizzarus.service.PizzasService;

@Controller
public class IndexController {

	@Autowired
	private PizzasService pizzasService;
	
	@RequestMapping(value = "/index.htm")
	public ModelAndView indexPage() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pizzas", pizzasService.getPizzas());
		return mav;
	}
}
