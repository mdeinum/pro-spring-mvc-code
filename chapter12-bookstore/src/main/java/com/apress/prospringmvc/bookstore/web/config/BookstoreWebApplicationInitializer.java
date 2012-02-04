package com.apress.prospringmvc.bookstore.web.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.bookstore.config.InfrastructureContextConfiguration;
import com.apress.prospringmvc.bookstore.config.TestDataContextConfiguration;

/**
 * The main {@link WebApplicationInitializer} which starts up a {@link AnnotationConfigWebApplicationContext}. Resources
 * for this context are retrieved from annotated classes which are annotated using the {@link Configuration}. The
 * classes loaded are mentioned here are stored in the {@link #configurationClasses}
 * <p/>
 * 
 * Finally we also programmatically configure the {@link DispatcherServlet} that listens to /
 * 
 * @author Koen Serneels
 */

public class BookstoreWebApplicationInitializer implements WebApplicationInitializer {

	private static final Class<?>[] configurationClasses = new Class<?>[] { TestDataContextConfiguration.class,
			WebMvcContextConfiguration.class, InfrastructureContextConfiguration.class,
			WebflowContextConfiguration.class };

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(configurationClasses);

		servletContext.addListener(new ContextLoaderListener(rootContext));
	
		// OEMIV
		FilterRegistration.Dynamic penEntityManagerInViewFilter = servletContext.addFilter(
				"openEntityManagerInViewFilter", new OpenEntityManagerInViewFilter());
		penEntityManagerInViewFilter.addMappingForUrlPatterns(null, false, "/*");

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("bookstore", new DispatcherServlet(
				rootContext));

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
