package com.baiyun.gcd.tmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/product/list");
        registry.addViewController("/index.html").setViewName("redirect:/product/list");
        registry.addViewController("/manager").setViewName("manager");
        registry.addViewController("tologin").setViewName("login");
        registry.addViewController("toManagerLogin").setViewName("managerLogin");
    }
}
