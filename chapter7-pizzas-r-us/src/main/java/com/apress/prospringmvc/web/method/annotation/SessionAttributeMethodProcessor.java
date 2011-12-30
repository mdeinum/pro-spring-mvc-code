package com.apress.prospringmvc.web.method.annotation;

import java.lang.reflect.Method;

import javax.servlet.ServletException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.Conventions;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.apress.prospringmvc.web.bind.annotation.SessionAttribute;

public class SessionAttributeMethodProcessor extends AbstractNamedValueMethodArgumentResolver implements
        HandlerMethodReturnValueHandler {

    public SessionAttributeMethodProcessor(final ConfigurableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SessionAttribute.class);
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(final MethodParameter parameter) {
        return new SessionAttributeNamedValueInfo(parameter.getParameterAnnotation(SessionAttribute.class));
    }

    @Override
    protected Object resolveName(final String name, final MethodParameter parameter, final NativeWebRequest request)
            throws Exception {

        return request.getAttribute(name, NativeWebRequest.SCOPE_SESSION);
    }

    @Override
    protected void handleMissingValue(final String name, final MethodParameter parameter) throws ServletException {
        throw new IllegalStateException("No session attribute '" + name + "' found");
    }

    private static class SessionAttributeNamedValueInfo extends NamedValueInfo {

        public SessionAttributeNamedValueInfo(final SessionAttribute annotation) {
            super(annotation.value(), annotation.required(), annotation.defaultValue());
        }
    }

    @Override
    public boolean supportsReturnType(final MethodParameter returnType) {
        return returnType.getMethodAnnotation(SessionAttribute.class) != null;
    }

    @Override
    public void handleReturnValue(final Object returnValue, final MethodParameter returnType,
            final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest) throws Exception {
        String name = getNameForReturnValue(returnValue, returnType);
        webRequest.setAttribute(name, returnValue, NativeWebRequest.SCOPE_SESSION);
    }

    private String getNameForReturnValue(final Object returnValue, final MethodParameter returnType) {
        SessionAttribute annot = returnType.getMethodAnnotation(SessionAttribute.class);
        if (annot != null && StringUtils.hasText(annot.value())) {
            return annot.value();
        } else {
            Method method = returnType.getMethod();
            Class<?> resolvedType = GenericTypeResolver.resolveReturnType(method, returnType.getDeclaringClass());
            return Conventions.getVariableNameForReturnType(method, resolvedType, returnValue);
        }

    }

}
