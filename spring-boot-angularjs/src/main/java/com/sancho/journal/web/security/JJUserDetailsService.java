package com.sancho.journal.web.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sancho.journal.web.dao.UserRepository;
import com.sancho.journal.web.model.User;

@Service("userDetailsService")
// @EnableGlobalAuthentication
public class JJUserDetailsService implements UserDetailsService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JJUserDetailsService.class);
    
    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        LOGGER.debug("###JJUserDetailsService");
        LOGGER.debug("username: " + username);
        User user = userRepository.findByUsername(username);
        
        if (user != null) {
            LOGGER.debug("###USER NOT NULL");
            GrantedAuthority roleAuth = new SimpleGrantedAuthority(user.getRole());
            Collection<GrantedAuthority> ac = new ArrayList<>();
            ac.add(roleAuth);
            
            UserDetails ud = new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), ac);
            LOGGER.debug("### returning user details : " + ud);
            return ud;
        } else {
            UsernameNotFoundException ue = new UsernameNotFoundException("username: " + username + "  not found");
            LOGGER.debug("UsernameNotFoundException:" + ue.getMessage());
            throw ue;
        }
    }
    
}
