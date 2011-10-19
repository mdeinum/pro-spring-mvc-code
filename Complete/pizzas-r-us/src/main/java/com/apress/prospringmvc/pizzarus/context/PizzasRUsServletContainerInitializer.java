package com.apress.prospringmvc.pizzarus.context;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.DispatcherServlet;

public class PizzasRUsServletContainerInitializer implements ServletContainerInitializer {

  @Override
  public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
      ServletRegistration.Dynamic  registration = servletContext.addServlet("pizzas", DispatcherServlet.class);
      registration.addMapping("/*");
      registration.setLoadOnStartup(1);
  }
}
