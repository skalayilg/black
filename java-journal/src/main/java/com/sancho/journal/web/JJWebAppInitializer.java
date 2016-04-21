package com.sancho.journal.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class JJWebAppInitializer implements WebApplicationInitializer {
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
                
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
        
    }
    
    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.scan("com.sancho.journal.web");
        return webContext;
    }
    
}
