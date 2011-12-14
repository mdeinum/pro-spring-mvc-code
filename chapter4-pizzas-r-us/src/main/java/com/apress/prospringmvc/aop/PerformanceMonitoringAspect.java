package com.apress.prospringmvc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.JamonPerformanceMonitorInterceptor;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.stereotype.Component;

/**
 * Simple aspect to do performance logging. 
 * 
 * For more elaborate functionality use the {@link PerformanceMonitorInterceptor} or 
 * the {@link JamonPerformanceMonitorInterceptor} from spring.
 * 
 * @author M. Deinum
 */
@Aspect
@Component
public class PerformanceMonitoringAspect {

    private final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Pointcut("execution(* com.apress.prospringmvc.pizzarus.web.*.*(..))")
    public void allMethods() {
    }

    @Around("allMethods()")
    public Object performance(final ProceedingJoinPoint pjp) throws Throwable {
        final long start = System.currentTimeMillis();

        try {
            return pjp.proceed();
        } finally {
            final long end = System.currentTimeMillis();
            this.logger.debug("Method {} took {} ms.", pjp.getSignature(), (end - start));
        }
    }

}
