package com.apress.prospringmvc.bookstore.web;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * {@link ServletContainerInitializer} implementation to show bootstrapping of dispatcher servlet. 
 * 
 * Note: To see it working comment in this code and comment out the code inside the {@link BookstoreWebApplicationInitializer} 
 * else the servlet will be initialized 2 times which will result in an exception.
 * 
 * @author M. Deinum
 *
 */
public class BookstoreServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
        //        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", DispatcherServlet.class);
        //        dispatcher.setLoadOnStartup(1);
        //        dispatcher.addMapping("/*");
    }

}
