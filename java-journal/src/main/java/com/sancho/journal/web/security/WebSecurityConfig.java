package com.sancho.journal.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.sancho.journal.web.security")

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private static final String INDEX_HTML = "/index.html";
    
    @Autowired
    @Qualifier("userDetailsService")
    
    UserDetailsService userDetailsService;
    
    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        
        web.ignoring().antMatchers(INDEX_HTML).antMatchers("/**/*.*"); // #3
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/signup", "/about", "/status", "/status/auth").permitAll() // #4
                // .antMatchers("/journal/published").hasRole("publisher") // #6
                .antMatchers("/journal/published").hasAuthority("publisher")
                .anyRequest().authenticated() // 7
                .and().formLogin() // #8
                .loginPage(INDEX_HTML) // #9
                .usernameParameter("username").passwordParameter("password").loginProcessingUrl("/signin")
                .defaultSuccessUrl(INDEX_HTML).permitAll() // #5
                .and().logout().logoutUrl("/logout").logoutSuccessUrl(INDEX_HTML).and().csrf().disable();
    }
    
}
