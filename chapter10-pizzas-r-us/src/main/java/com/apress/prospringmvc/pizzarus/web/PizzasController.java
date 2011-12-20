package com.apress.prospringmvc.pizzarus.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
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

	@ExceptionHandler
	public ModelAndView handleIOException(DataAccessException ex, Principal principal, WebRequest request) {
		ModelAndView mav = new ModelAndView("io-error");
		mav.addObject("username", principal.getName());
		mav.addAllObjects(request.getParameterMap());
		for(Iterator<String> names = request.getHeaderNames(); names.hasNext(); ) {
			String name =  names.next();
			String[] value = request.getHeaderValues(name);
			mav.addObject(name, value);
		}
		return mav;
		
	}
	
}
