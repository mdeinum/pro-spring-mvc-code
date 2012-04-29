package com.apress.prospringmvc.bookstore.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the homepage
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Controller
public class MainController {
	@RequestMapping("index.htm")
	public ModelAndView main() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("main");
		return mov;
	}
}
