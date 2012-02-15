package com.apress.prospringmvc.bookstore.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.bookstore.config.InfrastructureContextConfiguration;
import com.apress.prospringmvc.bookstore.web.config.WebMvcContextConfiguration;

public class BookstoreWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        registerListener(servletContext);
        registerDispatcherServlet(servletContext);
    }

    private void registerDispatcherServlet(final ServletContext servletContext) {
        AnnotationConfigWebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(
                dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private void registerListener(final ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = createContext(InfrastructureContextConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
    }

    /**
     * Factory method to create {@link AnnotationConfigWebApplicationContext} instances. 
     * @param annotatedClasses
     * @return
     */
    private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.getEnvironment().setActiveProfiles("local");
        context.register(annotatedClasses);
        return context;
    }

}
