package com.apress.prospringmvc.pizzarus.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.pizzarus.config.CustomComponentScanFilter;
import com.apress.prospringmvc.pizzarus.config.InfrastructureContextConfiguration;
import com.apress.prospringmvc.pizzarus.config.RepositoryConfiguration;
import com.apress.prospringmvc.pizzarus.config.TestDataContextConfiguration;

public class PizzaRUsWebApplicationInitializer implements WebApplicationInitializer {

	private static final Class<?>[] configurationClasses = new Class<?>[] { TestDataContextConfiguration.class,
			WebMvcContextConfiguration.class, InfrastructureContextConfiguration.class,
			WebflowContextConfiguration.class, RepositoryConfiguration.class, CustomComponentScanFilter.class };

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(configurationClasses);

		servletContext.addListener(new ContextLoaderListener(rootContext));

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("pizza-r-us", new DispatcherServlet(
				rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
