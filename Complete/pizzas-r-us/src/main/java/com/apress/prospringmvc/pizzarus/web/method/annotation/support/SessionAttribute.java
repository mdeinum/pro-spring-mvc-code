package com.apress.prospringmvc.pizzarus.web.method.annotation.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Parameters annotated with this annotation are retrieved (or stored) in the Session.
 * 
 * @author marten
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionAttribute {

  String value() default "";
  
  boolean required() default false;
    
}
