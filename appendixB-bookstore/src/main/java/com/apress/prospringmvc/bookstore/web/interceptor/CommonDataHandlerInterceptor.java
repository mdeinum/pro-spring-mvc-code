package com.apress.prospringmvc.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apress.prospringmvc.bookstore.service.BookstoreService;

public class CommonDataHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BookstoreService bookstoreService;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			modelAndView.addObject("randomBooks",
					this.bookstoreService.findRandomBooks());
		}
	}
}
