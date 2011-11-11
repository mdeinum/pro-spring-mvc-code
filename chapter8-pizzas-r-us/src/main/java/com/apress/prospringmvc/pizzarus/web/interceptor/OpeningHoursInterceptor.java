package com.apress.prospringmvc.pizzarus.web.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriTemplate;

/**
 * Interceptor which disables request processing when we aren't open. 
 * 
 * Openinghours for our pizza shop are after 3PM and till 2AM.
 *  
 * @author M. Deinum
 * @author C. Yates
 */
public class OpeningHoursInterceptor extends HandlerInterceptorAdapter {

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		
		if (hour > 2 && hour < 15) {
			UriTemplate.
			response.sendRedirect("")
			return false;
		}
		return true;
	}
	
}
