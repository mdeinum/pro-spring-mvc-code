package com.apress.prospringmvc.pizzarus.web.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 21-12-11
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {

    @@RequestMapping("/index.htm")
    public ModelAndView index() throws Exception {
        return new ModelAndView("index");
    }

}
