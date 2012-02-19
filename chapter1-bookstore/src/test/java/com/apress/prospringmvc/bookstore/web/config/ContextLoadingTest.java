package com.apress.prospringmvc.bookstore.web.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { WebMvcContextConfiguration.class })
public class ContextLoadingTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testLoading() {
        assertNotNull(this.context);
    }
}
