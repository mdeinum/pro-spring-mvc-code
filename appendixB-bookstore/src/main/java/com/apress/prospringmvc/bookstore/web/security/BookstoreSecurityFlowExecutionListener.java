package com.apress.prospringmvc.bookstore.web.security;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.Expression;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.webflow.execution.RequestContextHolder;
import org.springframework.webflow.security.SecurityFlowExecutionListener;
import org.springframework.webflow.security.SecurityRule;

public class BookstoreSecurityFlowExecutionListener extends SecurityFlowExecutionListener implements
		ApplicationContextAware {

	private DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler;

	@Override
	protected void decide(SecurityRule rule, Object object) {
		if (getAccessDecisionManager() == null) {
			super.decide(rule, object);
			return;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		@SuppressWarnings("unchecked")
		Collection<ConfigAttribute> configAttributes = buildWebExpressionConfigAttribute(getConfigAttributes(rule));

		HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestContext().getExternalContext()
				.getNativeRequest();
		HttpServletResponse response = (HttpServletResponse) RequestContextHolder.getRequestContext()
				.getExternalContext().getNativeResponse();

		getAccessDecisionManager().decide(authentication, new FilterInvocation(request, response, new FilterChain() {
			@Override
			public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
				throw new UnsupportedOperationException();
			}
		}), configAttributes);

	}

	private Collection<ConfigAttribute> buildWebExpressionConfigAttribute(Collection<SecurityConfig> configAttributes) {
		Collection<ConfigAttribute> result = new ArrayList<ConfigAttribute>();

		for (SecurityConfig configAttribute : configAttributes) {
			try {
				Constructor<?> constructor = ClassUtils.getClass(
						"org.springframework.security.web.access.expression.WebExpressionConfigAttribute")
						.getConstructor(Expression.class);
				AccessibleObject.setAccessible(new AccessibleObject[] { constructor }, true);
				result.add((ConfigAttribute) constructor.newInstance(defaultWebSecurityExpressionHandler
						.getExpressionParser().parseExpression(configAttribute.getAttribute())));
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		super.setAccessDecisionManager(applicationContext.getBean(AccessDecisionManager.class));
		this.defaultWebSecurityExpressionHandler = applicationContext
				.getBean(DefaultWebSecurityExpressionHandler.class);

	}
}
