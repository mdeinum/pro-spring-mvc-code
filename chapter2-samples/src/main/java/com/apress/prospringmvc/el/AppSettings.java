package com.apress.prospringmvc.el;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppSettings {

    @Value("#{systemProperties.gopherProxySet}")
    private String username;

    @Value("#{systemProperties}")
    private Properties props;

    @PostConstruct
    private void init() {
        List<String> keys = new ArrayList<String>(this.props.size());
        for (Object key : this.props.keySet()) {
            keys.add((String) key);
        }

        Collections.sort(keys);
        for (String key : keys) {
            System.out.println(key + "=" + this.props.get(key));
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

}
