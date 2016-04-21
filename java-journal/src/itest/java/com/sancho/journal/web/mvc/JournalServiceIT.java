package com.sancho.journal.web.mvc;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;

import com.sancho.journal.web.dao.RepositoryIT;
import com.sancho.test.RetryTest;

@SpringApplicationConfiguration(classes = JournalService.class)
public class JournalServiceIT extends RepositoryIT {
    
    @Autowired
    JournalService journalService;
    
    @Test(retryAnalyzer = RetryTest.class)
    public void findAllJounals() {
        assertNotNull(journalService);
        assertEquals(journalService.findAllJounals().size(), 6);
        
    }
    
    @Test(retryAnalyzer = RetryTest.class)
    public void findAllTopics() {
        assertEquals(journalService.findAllJounals().size(), 6);
        
    }
    
    @Test(retryAnalyzer = RetryTest.class)
    public void findPublishedJounalsByUser() {
        assertEquals(journalService.findPublishedJounalsByUser("peter").size(), 3);
        
    }
    
    @Test(retryAnalyzer = RetryTest.class)
    public void findSubscribedTopics() {
        assertEquals(journalService.findSubscribedTopics("alex").size(), 3);
    }
    
    @Test(retryAnalyzer = RetryTest.class)
    public void findsubscribedJounalsByUser() {
        assertEquals(journalService.findsubscribedJounalsByUser("alex").size(), 4);
    }
}
