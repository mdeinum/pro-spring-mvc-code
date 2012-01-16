package com.apress.prospringmvc.bookstore.web.config;

import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.apress.prospringmvc.bookstore.web.interceptor.CommonDataHandlerIntereceptor;
import com.apress.prospringmvc.bookstore.web.interceptor.SecurityHandlerInterceptor;
import com.apress.prospringmvc.context.RequestHandledEventListener;

/**
 * WebMvc Configuration.
 * 
 * @author M. Deinum
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.apress.prospringmvc.bookstore.web" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public RequestHandledEventListener requestHandledEventListener() {
        return new RequestHandledEventListener();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**/*").addResourceLocations("classpath:/META-INF/web-resources/");
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/index.htm").setViewName("index");
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonDataHandlerIntereceptor());
        registry.addInterceptor(securityHandlerInterceptor()).addPathPatterns("/customer/account*", "/cart/checkout");
    }

    @Bean
    public CommonDataHandlerIntereceptor commonDataHandlerIntereceptor() {
        return new CommonDataHandlerIntereceptor();
    }

    @Bean
    public SecurityHandlerInterceptor securityHandlerInterceptor() {
        return new SecurityHandlerInterceptor();
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("AuthenticationException", "login");

        Properties statusCodes = new Properties();
        mappings.setProperty("login", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));

        exceptionResolver.setExceptionMappings(mappings);
        exceptionResolver.setStatusCodes(statusCodes);
        return exceptionResolver;
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
