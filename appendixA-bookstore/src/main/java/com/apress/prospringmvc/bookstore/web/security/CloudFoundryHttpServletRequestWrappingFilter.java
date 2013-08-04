package com.apress.prospringmvc.bookstore.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Wraps the {@link HttpServletRequest} and checks for any available headers that indicate of the original connection is
 * over SSL. If so we tell Spring Security to avoid ending up in endless loop
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
public class CloudFoundryHttpServletRequestWrappingFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		chain.doFilter(new CloudFoundryHttpServletRequestWrapper((HttpServletRequest) request), response);
	}

	static class CloudFoundryHttpServletRequestWrapper extends HttpServletRequestWrapper {

		private static final String[] TLS_HEADERS = new String[] { "sslclientcipher", "sslclientcertstatus" };

		public CloudFoundryHttpServletRequestWrapper(HttpServletRequest httpServletRequest) {
			super(httpServletRequest);
		}

		@Override
		public boolean isSecure() {
			boolean isSecure = super.isSecure();
			if (!isSecure) {
				boolean isActuallySecure = true;
				for (String header : TLS_HEADERS) {
					isActuallySecure = isActuallySecure && StringUtils.isNotBlank(getHeader(header));
				}
				isSecure = isActuallySecure;
			}
			return isSecure;
		}
	}
}