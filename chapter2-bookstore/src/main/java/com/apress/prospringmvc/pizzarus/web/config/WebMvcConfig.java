package com.apress.prospringmvc.pizzarus.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

/**
 * WebMvc Configuration.
 * 
 * @author M. Deinum
 */
@Configuration
@EnableAspectJAutoProxy
public class WebMvcConfig {

    @Value("#{systemProperties['user.name']}")
    private String username;

    @Configuration
    @Profile("local")
    public static class LocalConfig {
    }

    @Configuration
    @Profile("test")
    public static class TestConfig {
    }

    @Configuration
    @Profile("cloud")
    public static class CloudConfig {

    }

}
