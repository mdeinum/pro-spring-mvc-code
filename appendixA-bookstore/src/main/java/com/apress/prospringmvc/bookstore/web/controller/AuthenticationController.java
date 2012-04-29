package com.apress.prospringmvc.bookstore.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apress.prospringmvc.bookstore.service.BookstoreService;

/**
 * This controller talks to the {@link BookstoreService} to authenticate a user. This controller can be used via Spring
 * MVC (request mapping login.html) or as POJO for example via Web Flow
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Controller
public class AuthenticationController {

	@RequestMapping("public/authentication/login.htm")
	public ModelAndView authentication() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("login");
		return mov;
	}
}
