package com.sancho.journal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sancho.journal.web.mvc")
@PropertySource("classpath:config.properties")
@PropertySource(value = "classpath:${ENV}.config.properties", ignoreResourceNotFound = true)
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:${ENV}.db.properties", ignoreResourceNotFound = true)
@EnableTransactionManagement
@EnableJpaRepositories("com.sancho.journal.web.dao")

public class JJWebAppConfig extends WebMvcConfigurerAdapter {
    
    @Autowired
    Environment env;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/ang/");
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
        
    }
    
}
