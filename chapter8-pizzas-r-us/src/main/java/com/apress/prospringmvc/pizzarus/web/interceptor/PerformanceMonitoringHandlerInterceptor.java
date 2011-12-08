package com.apress.prospringmvc.pizzarus.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

public class PerformanceMonitoringHandlerInterceptor extends HandlerInterceptorAdapter {

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		MonitorFactory.getM
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		MonitorFactory.get
	}
	
}
