package com.sancho.journal;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class ApplicationBoot {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBoot.class);
    
    private ApplicationBoot() {
        // making contructor private
    }
    
    public static void main(String[] args) {
        
        SpringApplication sapp = new SpringApplication(ApplicationBoot.class);
        ApplicationContext ctx = sapp.run(args);
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Let's inspect the beans provided by Spring Boot:");
            
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug(beanName);
                
        }
    }
    
}
