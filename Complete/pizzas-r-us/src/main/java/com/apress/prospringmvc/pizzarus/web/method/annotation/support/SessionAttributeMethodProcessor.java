package com.apress.prospringmvc.pizzarus.web.method.annotation.support;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Conventions;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

public class SessionAttributeMethodProcessor implements HandlerMethodArgumentResolver, HandlerMethodReturnValueHandler {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(SessionAttribute.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) throws Exception {

    SessionAttribute attribute = parameter.getParameterAnnotation(SessionAttribute.class);

    String name = resolveNameForParameter(parameter);
    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    if (attribute.required()) {
      return WebUtils.getRequiredSessionAttribute(request, name);
    } else {
      return WebUtils.getSessionAttribute(request, name);
    }
  }

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return returnType.getMethodAnnotation(SessionAttribute.class) != null;
  }

  @Override
  public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest) throws Exception {
    
    String name = resolveNameForReturnValue(returnType, returnValue);
    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    WebUtils.setSessionAttribute(request, name, returnValue);
  }

  private String resolveNameForReturnValue(MethodParameter returnType, Object returnValue) {
    SessionAttribute annot = returnType.getMethodAnnotation(SessionAttribute.class);
    if (annot != null && StringUtils.hasText(annot.value())) {
      return annot.value();
    }
    else {
      Method method = returnType.getMethod();
      Class<?> resolvedType = GenericTypeResolver.resolveReturnType(method, returnType.getDeclaringClass());
      return Conventions.getVariableNameForReturnType(method, resolvedType, returnValue);
    }
  }

  private String resolveNameForParameter(MethodParameter parameter) {
    SessionAttribute annot = parameter.getParameterAnnotation(SessionAttribute.class);
    String attrName = (annot != null) ? annot.value() : null;
    return StringUtils.hasText(attrName) ? attrName :  Conventions.getVariableNameForParameter(parameter);
  }
  
}
