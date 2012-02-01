package com.apress.prospringmvc.bookstore.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/spring/spring-security.xml")
@ComponentScan("com.apress.prospringmvc.bookstore.web.security")
public class SpringSecurityConfiguration {

}
