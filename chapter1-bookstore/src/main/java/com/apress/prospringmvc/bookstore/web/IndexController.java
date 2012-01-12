package com.apress.prospringmvc.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping(value = "/index.htm")
    public ModelAndView indexPage() {
        return new ModelAndView("/WEB-INF/views/index.jsp");
    }
}
