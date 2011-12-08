package com.apress.prospringmvc.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Helper class to log some basic information about the {@link ApplicationContext}
 * 
 * @author M. Deinum
 *
 */
public class ApplicationContextLogger {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextLogger.class);
	
	public static void log(ApplicationContext context) {
		LOG.info("Context: {},{}", context.getClass(), context.getDisplayName());
		LOG.info("Beans: {}", context.getBeanDefinitionCount());
		for (String name : context.getBeanDefinitionNames()) {
			LOG.info("Bean: {}", name);
		}
	}
	
}
