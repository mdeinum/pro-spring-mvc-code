package com.apress.prospringmvc.bookstore.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Shows the main screen
 * 
 * @author Koen Serneels
 */

@Controller
public class MainController {
	@RequestMapping("index.htm")
	public ModelAndView main() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("main");
		return mov;
	}
	
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.htm";
    }
}
