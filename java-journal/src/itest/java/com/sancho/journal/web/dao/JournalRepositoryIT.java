package com.sancho.journal.web.dao;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class JournalRepositoryIT extends RepositoryIT {
    
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    UserRepository    userRepository;
    
    @Test
    public void countJournals() {
        assertEquals(journalRepository.count(), 6);
    }
    
    @Test
    public void findByUser() {
        assertEquals(journalRepository.findByUser(userRepository.findByUsername("alex")).size(), 0);
        assertEquals(journalRepository.findByUser(userRepository.findByUsername("phil")).size(), 3);
    }
    
    @Test(groups = { "repo" })
    public void findByTopicSubscribers() {
        assertEquals(journalRepository.findByTopicSubscribers(userRepository.findByUsername("alex")).size(), 4);
    }
    
}
