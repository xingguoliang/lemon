package com.mossle.security.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderFactoryBean implements FactoryBean,
        InitializingBean {
    private static Logger logger = LoggerFactory
            .getLogger(PasswordEncoderFactoryBean.class);
    private String type;
    private PasswordEncoder passwordEncoder;

    public void afterPropertiesSet() {
        if ("md5".equals(type)) {
            this.passwordEncoder = new MessageDigestPasswordEncoder("md5");
        } else {
            this.passwordEncoder = NoOpPasswordEncoder.getInstance();
        }

        logger.info("choose {}", passwordEncoder.getClass());
    }

    public Object getObject() {
        return passwordEncoder;
    }

    public Class getObjectType() {
        return PasswordEncoder.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SimplePasswordEncoder getSimplePasswordEncoder() {
        return new SimplePasswordEncoder(this.passwordEncoder);
    }
}