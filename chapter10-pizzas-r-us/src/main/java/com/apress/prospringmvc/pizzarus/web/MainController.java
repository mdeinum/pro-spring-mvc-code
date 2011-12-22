package com.apress.prospringmvc.pizzarus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("index.html")
	public ModelAndView main() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("main");
		return mov;
	}
}
