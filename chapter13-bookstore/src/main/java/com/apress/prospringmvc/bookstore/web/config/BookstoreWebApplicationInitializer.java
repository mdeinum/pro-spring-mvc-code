package com.apress.prospringmvc.bookstore.web.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.config.BeanIds;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.bookstore.config.InfrastructureContextConfiguration;
import com.apress.prospringmvc.bookstore.config.TestDataContextConfiguration;

/**
 * The main {@link WebApplicationInitializer} which starts up a {@link AnnotationConfigWebApplicationContext}. Resources
 * for this context are retrieved from annotated classes which are annotated using the {@link Configuration}. The
 * classes loaded are mentioned here are stored in the {@link #configurationClasses}</p>
 * 
 * The application context is then passed on to a {@link ContextLoaderListener} which is manually registered using the
 * JEE API for registering {@link ServletContextListener}. Using the same API we also programmatically configure the
 * {@link DispatcherServlet} that listens to / and register the {@link OpenEntityManagerInViewFilter}.
 * <p>
 * 
 * Finally we also register the Spring {@link DelegatingFilterProxy} filter which will be used by Spring security to add
 * the security filters.
 * 
 * @author Koen Serneels
 */

public class BookstoreWebApplicationInitializer implements WebApplicationInitializer {

	private static final Class<?>[] configurationClasses = new Class<?>[] { TestDataContextConfiguration.class,
			WebMvcContextConfiguration.class, InfrastructureContextConfiguration.class,
			WebflowContextConfiguration.class };

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerListener(servletContext);
		registerDispatcherServlet(servletContext);
		registerOpenEntityManagerInViewFilter(servletContext);
		registerSpringSecurityFilterChain(servletContext);
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
				new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	private void registerListener(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = createContext(configurationClasses);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		servletContext.addListener(new RequestContextListener());
	}

	private void registerOpenEntityManagerInViewFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic registration = servletContext.addFilter("openEntityManagerInView",
				new OpenEntityManagerInViewFilter());
		registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true,
				DISPATCHER_SERVLET_NAME);
	}

	private void registerSpringSecurityFilterChain(ServletContext servletContext) {
		FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter(
				BeanIds.SPRING_SECURITY_FILTER_CHAIN, new DelegatingFilterProxy());
		springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
	}

	/**
	 * Factory method to create {@link AnnotationConfigWebApplicationContext} instances.
	 * @param annotatedClasses
	 * @return
	 */
	private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(annotatedClasses);
		return context;
	}
}
