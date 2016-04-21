package com.sancho.journal.web.vo;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

public class TopicTest {
    
    @Test
    public void Topic() {
        new BeanTester().testBean(Topic.class);
        
    }
}
