package com.sancho.journal.web.dao;

import static org.testng.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class TopicRepositoryIT extends RepositoryIT {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicRepositoryIT.class);
    @Autowired
    TopicRepository             topicRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Test
    public void countTopics() {
        
        assertEquals(topicRepository.count(), 10);
    }
    
    @Test // (retryAnalyzer = com.sancho.itest.RetryTest.class)
    public void findBySubscribers() {
        assertEquals(topicRepository.findBySubscribers(userRepository.findByUsername("alex")).size(), 3);
    }
}
