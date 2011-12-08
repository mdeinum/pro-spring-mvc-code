package com.apress.prospringmvc.di.scanning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.prospringmvc.di.ApplicationContextLogger;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContextLogger.log(new AnnotationConfigApplicationContext(ApplicationConfig.class));
		ApplicationContextLogger.log(new ClassPathXmlApplicationContext("/com/apress/prospringmvc/di/scanning/application-context.xml"));
		
	}

}
