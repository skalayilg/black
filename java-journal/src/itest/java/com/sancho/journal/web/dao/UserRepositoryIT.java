package com.sancho.journal.web.dao;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class UserRepositoryIT extends RepositoryIT {
    
    @Autowired
    UserRepository userRepository;
    
    @Test
    public void countUsers() {
        assertEquals(userRepository.count(), 6);
    }
    
    @Test
    public void findByUsername() {
        assertEquals(userRepository.findByUsername("alex").getUsername(), "alex");
    }
    
    @Test(dependsOnMethods = "findByUsername")
    public void verifySubscription() {
        assertEquals(userRepository.findByUsername("alex").getTopicSubcriptions().size(), 3);
    }
    
}
