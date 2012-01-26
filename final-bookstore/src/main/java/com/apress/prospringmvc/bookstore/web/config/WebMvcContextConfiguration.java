package com.apress.prospringmvc.bookstore.web.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.apress.prospringmvc.bookstore.domain.Cart;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.web.interceptor.CommonDataHandlerInterceptor;
import com.apress.prospringmvc.bookstore.web.interceptor.SecurityHandlerInterceptor;
import com.apress.prospringmvc.bookstore.web.view.OrderExcelView;
import com.apress.prospringmvc.bookstore.web.view.OrderPdfView;
import com.apress.prospringmvc.bookstore.web.view.SimpleConfigurableViewResolver;
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

    /**
     * {@link org.springframework.context.ApplicationListener} implementation that listens 
     * for {@link org.springframework.web.context.support.RequestHandledEvent} events and logs them.
     * 
     * @return the {@link RequestHandledEventListener}.
     */
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
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(commonDataHandlerInterceptor());
        registry.addInterceptor(securityHandlerInterceptor()).addPathPatterns("/customer/account*", "/cart/checkout",
                "/order/*", "/order.*");
    }

    //-- Start Locale Support (I18N) --//

    /**
     * The {@link LocaleChangeInterceptor} allows for the locale to be changed. It provides a <code>paramName</code> property which sets 
     * the request parameter to check for changing the language, the default is <code>locale</code>.
     * @return the {@link LocaleChangeInterceptor}
     */
    @Bean
    public HandlerInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * The {@link LocaleResolver} implementation to use. Specifies where to store the current selectd locale.
     * 
     * @return the {@link LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    /**
     * To resolve message codes to actual messages we need a {@link MessageSource} implementation. The default 
     * implementations use a {@link java.util.ResourceBundle} to parse the property files with the messages in it.
     * @return the {@link MessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        return messageSource;
    }

    //-- End Locale Support (I18N) --//

    @Bean
    public CommonDataHandlerInterceptor commonDataHandlerInterceptor() {
        return new CommonDataHandlerInterceptor();
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

    /**
     * The {@link ContentNegotiatingViewResolver} will detect the other {@link ViewResolver} instances in the application context. 
     * 
     * It will delegate the lookup of view resolving to all the detected view resolvers and afterwards will pick the best match
     * 
     * @return the {@link ContentNegotiatingViewResolver}
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        List<View> defaultViews = new ArrayList<View>();
        MappingJacksonJsonView json = new MappingJacksonJsonView();
        json.setModelKey("order");
        defaultViews.add(json); //JSON support
        MarshallingView xml = new MarshallingView(xstreamMarshaller());
        xml.setModelKey("order");
        defaultViews.add(xml); // XML Support
        viewResolver.setDefaultViews(defaultViews);
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public XStreamMarshaller xstreamMarshaller() {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        try {
            marshaller.setAliases(Collections.singletonMap("order", Order.class));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        return marshaller;
    }

    @Bean
    public ViewResolver pdfViewResolver() {
        SimpleConfigurableViewResolver viewResolver = new SimpleConfigurableViewResolver();
        viewResolver.setViews(Collections.singletonMap("order", new OrderPdfView()));
        return viewResolver;
    }

    @Bean
    public ViewResolver xlsViewResolver() {
        SimpleConfigurableViewResolver viewResolver = new SimpleConfigurableViewResolver();
        viewResolver.setViews(Collections.singletonMap("order", new OrderExcelView()));
        return viewResolver;
    }

    @Bean(name = "order")
    public OrderPdfView orderPdfView() {
        return new OrderPdfView();
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Cart cart() {
        return new Cart();
    }
}
