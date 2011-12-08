package com.apress.prospringmvc.el;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SomeBean {

    @Value("#{appSettings.username}")
    private String username;

    @PostConstruct
    public void init() {
        System.out.println("Username = " + this.username);
    }
}
