package com.sancho.journal.web.model;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

public class UserTest {
    
    @Test
    public void User() {
        new BeanTester().testBean(User.class);
    }
}
