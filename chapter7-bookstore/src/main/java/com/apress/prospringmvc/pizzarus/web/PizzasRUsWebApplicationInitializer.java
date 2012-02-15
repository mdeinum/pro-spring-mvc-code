package com.apress.prospringmvc.pizzarus.web;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.pizzarus.config.InfrastructureContextConfiguration;
import com.apress.prospringmvc.pizzarus.web.config.WebMvcContextConfiguration;

public class PizzasRUsWebApplicationInitializer implements WebApplicationInitializer {

    private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5; //5 Mb file limit
    private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb start writing files to disk
    private static final long MAX_REQUEST_SIZE = -1L; //No request size limit

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
        dispatcher.setMultipartConfig(new MultipartConfigElement(null, MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE,
                FILE_SIZE_THRESHOLD));
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
