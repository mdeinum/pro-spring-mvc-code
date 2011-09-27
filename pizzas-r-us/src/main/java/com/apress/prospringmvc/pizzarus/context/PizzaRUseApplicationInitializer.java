package com.apress.prospringmvc.pizzarus.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.filter.*; 

/**
 * {@link WebApplicationInitializer} for bootstrapping our Pizza-R-Us web
 * application.
 * 
 * @author M. Deinum
 * @author C. Yates
 * 
 */
public class PizzaRUseApplicationInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {

    registerContextLoaderListener(servletContext);
    registerDispatcherServlet(servletContext);
    registerFilters(servletContext);

  }

  private void registerFilters(ServletContext servletContext) {
    CharacterEncodingFilter cef = new CharacterEncodingFilter();
    cef.setForceEncoding(true);
    cef.setEncoding("UTF8");
    servletContext.addFilter("cef", cef);

    HiddenHttpMethodFilter hhmf = new HiddenHttpMethodFilter();
    servletContext.addFilter("hhmf", hhmf);

  }

  private void registerContextLoaderListener(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.scan("com.apress.prospringmvc.pizzarus.root.config");
    
    servletContext.addListener(new ContextLoaderListener(rootContext));
  }

  private void registerDispatcherServlet(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
    webApplicationContext.scan("com.apress.prospringmvc.pizzarus.mvc.config");

    ServletRegistration reg = servletContext.addServlet("pizzas", new DispatcherServlet(webApplicationContext));
    reg.addMapping("/");

  }

}
