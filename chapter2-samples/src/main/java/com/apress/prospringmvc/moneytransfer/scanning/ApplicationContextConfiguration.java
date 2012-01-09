package com.apress.prospringmvc.moneytransfer.scanning;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.apress.prospringmvc.moneytransfer.scanning",
        "com.apress.prospringmvc.moneytransfer.repository" })
public class ApplicationContextConfiguration {

}
