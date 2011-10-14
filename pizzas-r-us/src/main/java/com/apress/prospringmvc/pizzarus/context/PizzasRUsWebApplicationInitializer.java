package com.apress.prospringmvc.pizzarus.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

public class PizzasRUsWebApplicationInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
      ServletRegistration.Dynamic  registration = servletContext.addServlet("pizzas", DispatcherServlet.class);
      registration.addMapping("/*");
      registration.setLoadOnStartup(1);
  }
}
