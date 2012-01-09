package com.apress.prospringmvc.pizzarus.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.pizzarus.web.config.WebMvcContext;

public class PizzaRUsWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebMvcContext.class);
		DispatcherServlet servlet = new DispatcherServlet(ctx);
		ServletRegistration.Dynamic registration = servletContext.addServlet("pizzas", servlet);
		registration.addMapping("/*");
		registration.setLoadOnStartup(1);
	}

}
