package com.apress.prospringmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * Helper class to log some basic information about the {@link ApplicationContext}
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public class ApplicationContextLogger {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextLogger.class);

    public static void log(ApplicationContext context) {
        LOG.info("Context: {},{}", context.getClass(), context.getDisplayName());
        LOG.info("Beans: {}", context.getBeanDefinitionCount());
        LOG.info("Active profiles: {}", (Object[])context.getEnvironment().getActiveProfiles());
        for (String name : context.getBeanDefinitionNames()) {
            LOG.info("Bean: {}", name);
        }
    }

    public static void log(BeanFactory factory) {
        if (factory instanceof ListableBeanFactory) {
            ListableBeanFactory lbf = (ListableBeanFactory) factory;
            LOG.info("Beans: {}", lbf.getBeanDefinitionCount());
            for (String name : lbf.getBeanDefinitionNames()) {
                LOG.info("Bean: {}", name);
            }
        } else {
            LOG.info("Not a ListableBeanFactory {}", factory);
        }

    }

}
