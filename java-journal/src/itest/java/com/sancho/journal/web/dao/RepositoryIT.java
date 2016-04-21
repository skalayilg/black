package com.sancho.journal.web.dao;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import com.sancho.journal.web.JJDatabaseConfig;

@ActiveProfiles("embedded")
@ContextConfiguration(classes = JJDatabaseConfig.class)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTests.sql")
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTests.sql")
public abstract class RepositoryIT extends AbstractTransactionalTestNGSpringContextTests {
    
    public RepositoryIT() {
        super();
    }
    
}