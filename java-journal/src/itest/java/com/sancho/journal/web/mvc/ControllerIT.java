package com.sancho.journal.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;

import com.sancho.journal.web.JJDatabaseConfig;
import com.sancho.journal.web.JJWebAppConfig;

@ActiveProfiles("embedded")
@ContextConfiguration(classes = { JJDatabaseConfig.class, JJWebAppConfig.class })
@WebAppConfiguration
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTests.sql")
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTests.sql")
public abstract class ControllerIT extends AbstractTransactionalTestNGSpringContextTests {
    
    public ControllerIT() {
        super();
    }
    
    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc               mockMvc;
    
    @BeforeMethod
    public void setup() {
        
        if (mockMvc == null)
            mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
}