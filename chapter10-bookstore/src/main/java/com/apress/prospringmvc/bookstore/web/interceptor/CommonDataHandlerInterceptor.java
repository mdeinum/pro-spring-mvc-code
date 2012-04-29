package com.apress.prospringmvc.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apress.prospringmvc.bookstore.service.BookstoreService;

/**
 * Makes the random books available on the Servlet requet
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
public class CommonDataHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BookstoreService bookstoreService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("randomBooks", this.bookstoreService.findRandomBooks());
		return super.preHandle(request, response, handler);
	}
}
