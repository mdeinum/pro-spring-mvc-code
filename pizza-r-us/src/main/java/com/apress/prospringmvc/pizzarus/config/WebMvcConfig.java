package com.apress.prospringmvc.pizzarus.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.Log4jNestedDiagnosticContextInterceptor;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public HandlerMapping handlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
		handlerMapping.setInterceptors(getAllInterceptors());
		return handlerMapping;
	}

	private Object[] getAllInterceptors() {
		List allInterceptors = new ArrayList();
		allInterceptors.addAll(Arrays.asList(getHandlerInterceptors()));
		allInterceptors.addAll(Arrays.asList(getWebRequestInterceptors()));
		return allInterceptors.toArray(new Object[allInterceptors.size()]);
	}

	private HandlerInterceptor[] getHandlerInterceptors() {
		Log4jNestedDiagnosticContextInterceptor webRequestInterceptor = new Log4jNestedDiagnosticContextInterceptor();
		webRequestInterceptor.setIncludeClientInfo(true);
		HandlerInterceptor interceptor = new WebRequestHandlerInterceptorAdapter(
				webRequestInterceptor);
		return new HandlerInterceptor[] { interceptor };
	}

	private WebRequestInterceptor[] getWebRequestInterceptors() {
		Log4jNestedDiagnosticContextInterceptor webRequestInterceptor = new Log4jNestedDiagnosticContextInterceptor();
		webRequestInterceptor.setIncludeClientInfo(true);
		return new WebRequestInterceptor[] { webRequestInterceptor };
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		for (HandlerInterceptor interceptor : getHandlerInterceptors()) {
			registry.addInterceptor(interceptor);
		}

		for (WebRequestInterceptor interceptor : getWebRequestInterceptors()) {
			registry.addWebRequestInterceptor(interceptor);
		}
	}

}
