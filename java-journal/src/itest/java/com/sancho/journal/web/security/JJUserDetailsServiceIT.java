package com.sancho.journal.web.security;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import com.sancho.journal.web.dao.RepositoryIT;

@ContextConfiguration(classes = JJUserDetailsService.class)
public class JJUserDetailsServiceIT extends RepositoryIT {
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Test
    public void loadUserByUsername() {
        assertNotNull(userDetailsService);
        UserDetails ud = userDetailsService.loadUserByUsername("alex");
        assertEquals(ud.getUsername(), "alex");
        
    }
    
    @Test(expectedExceptions = UsernameNotFoundException.class)
    public void loadUserByUsername_Invalid() {
        assertNotNull(userDetailsService);
        userDetailsService.loadUserByUsername("NON_EXISTING");
        
    }
}
