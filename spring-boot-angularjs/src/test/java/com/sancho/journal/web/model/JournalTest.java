package com.sancho.journal.web.model;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

public class JournalTest {
    
    @Test
    public void Journal() {
        new BeanTester().testBean(Journal.class);
    }
}
