package com.apress.prospringmvc.pizzarus.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.pizzarus.config.InfrastructureContext;
import com.apress.prospringmvc.pizzarus.web.config.WebMvcContext;

public class PizzaRUsWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.setConfigLocation("classpath:/spring/applicationContext.xml");
		rootContext.register(InfrastructureContext.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("pizza-r-us", new DispatcherServlet(
				rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");
	}

}
