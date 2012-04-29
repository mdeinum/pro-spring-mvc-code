package com.apress.prospringmvc.bookstore.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.apress.prospringmvc.bookstore.converter.StringToEntityConverter;
import com.apress.prospringmvc.bookstore.domain.Category;

/**
 * Spring MVC configuration
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.apress.prospringmvc.bookstore.web" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

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
     * The {@link LocaleResolver} implementation to use. Specifies where to store the current selected locale.
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
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    //-- End Locale Support (I18N) --//

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(categoryConverter());
        registry.addFormatter(new DateFormatter("dd-MM-yyyy"));
    }

    @Bean
    public StringToEntityConverter categoryConverter() {
        return new StringToEntityConverter(Category.class);
    }

}
