package com.sancho.journal.web.mvc;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/status")

public class LoginController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String, String> checkAuthenticationStatus(HttpServletRequest request, Authentication auth) {
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("###         inside status/       ###############");
        Map<String, String> out = new HashMap<>();
        Principal p = request.getUserPrincipal();
        if (p != null) {
            out.put("status", "loggedin");
            out.put("user", p.getName());
        } else {
            out.put("status", "loggedoff");
        }
        if (auth != null) {
            
            GrantedAuthority[] a = auth.getAuthorities().toArray(new GrantedAuthority[auth.getAuthorities().size()]);
            if (a.length > 0) {
                String role = a[0].getAuthority();
                out.put("roles", role);
            }
            
        }
        
        return out;
    }
    
}
