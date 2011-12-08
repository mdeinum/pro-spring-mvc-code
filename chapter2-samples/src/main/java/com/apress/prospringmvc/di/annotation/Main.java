package com.apress.prospringmvc.di.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.prospringmvc.di.ApplicationContextLogger;

public class Main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		ApplicationContextLogger.log(new AnnotationConfigApplicationContext(
				ApplicationConfig.class));
		ApplicationContextLogger
				.log(new ClassPathXmlApplicationContext(
						"/com/apress/prospringmvc/di/annotation/application-context.xml"));

	}

}
