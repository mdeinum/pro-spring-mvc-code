package com.apress.prospringmvc.pizzarus.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * {@link WebApplicationInitializer} for bootstrapping our Pizza-R-Us web application.
 * 
 * @author M. Deinum
 * @author C. Yates
 *
 */
public class PizzaRUseApplicationInitializer implements
		WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		registerContextLoaderListener(servletContext);
		registerDispatcherServlet(servletContext);
		
	}

	private void registerContextLoaderListener(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.scan("com.apress.prospringmvc.pizzarus.config");

		ContextLoaderListener cll = new ContextLoaderListener();
		servletContext.addListener(cll);		
		
	}
	
	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.scan("com.apress.prospringmvc.pizzarus.config");
		
		ServletRegistration reg = servletContext.addServlet("pizzas", new DispatcherServlet(webApplicationContext));
		reg.addMapping("/*");
		
	}
	
}
