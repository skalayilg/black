package com.sancho.journal.web.security;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
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
import org.testng.annotations.Test;

import com.sancho.journal.web.JJDatabaseConfig;
import com.sancho.journal.web.JJWebAppConfig;
import com.sancho.test.RetryTest;

@ActiveProfiles("embedded")
@ContextConfiguration(classes = { JJDatabaseConfig.class, JJWebAppConfig.class, WebSecurityConfig.class })
@WebAppConfiguration
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTests.sql")
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTests.sql")
public class SecurityIT extends AbstractTransactionalTestNGSpringContextTests {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityIT.class);
    
    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc               mockMvc;
    
    @BeforeMethod
    public void setup() {
        
        if (mockMvc == null)
            mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }
    
    @Test
    public void login() throws Exception {
        mockMvc.perform(post("/logout"));
        mockMvc.perform(get("/status")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedoff")))
                .andExpect(content().json("{'status':'loggedoff'}"));
                
        MockHttpSession session = getLoginSession("alex", "123abc");
        
        mockMvc.perform(get("/status").session(session)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedin")))
                .andExpect(content().json("{'roles':'subscriber','user':'alex','status':'loggedin'}"));
                
    }
    
    private MockHttpSession getLoginSession(String username, String password) throws Exception {
        MockHttpSession session = (MockHttpSession) mockMvc
                .perform(post("/signin").param("username", username).param("password", password))
                .andExpect(status().isFound())
                .andReturn().getRequest().getSession();
        return session;
    }
    
    private MockHttpSession getLogoutSession(MockHttpSession session) throws Exception {
        return (MockHttpSession) mockMvc
                .perform(post("/logout").session(session))
                .andExpect(status().isFound())
                .andReturn().getRequest().getSession();
    }
    
    @Test
    public void logout() throws Exception {
        MockHttpSession session = getLoginSession("alex", "123abc");
        
        mockMvc.perform(get("/status").session(session)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedin")))
                .andExpect(content().json("{'roles':'subscriber','user':'alex','status':'loggedin'}"));
                
        MockHttpSession session2 = getLogoutSession(session);
        
        mockMvc.perform(get("/status").session(session)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedoff")))
                .andExpect(content().json("{'status':'loggedoff'}"));
                
    }
    
    @Test() // (retryAnalyzer = RetryTest.class)
    public void accessToPublishedJournalsAsPublisher() throws Exception {
        MockHttpSession session = getLoginSession("peter", "123abc");
        
        mockMvc.perform(get("/status").session(session)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedin")))
                .andExpect(content().json("{'roles':'publisher','user':'peter','status':'loggedin'}"));
                
        mockMvc.perform(get("/journal/published").session(session)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
                
    }
    
    @Test(retryAnalyzer = RetryTest.class)
    public void accessToPublishedJournalsAsSubscriber() throws Exception {
        MockHttpSession session = getLoginSession("alex", "123abc");
        
        mockMvc.perform(get("/status").session(session)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedin")))
                .andExpect(content().json("{'roles':'subscriber','user':'alex','status':'loggedin'}"));
                
        mockMvc.perform(get("/journal/published").session(session)).andDo(print())
                .andExpect(status().isForbidden());
    }
    
    @Test(retryAnalyzer = RetryTest.class)
    public void accessToSubscribedJournalsAsSubscriber() throws Exception {
        MockHttpSession session = getLoginSession("alex", "123abc");
        
        mockMvc.perform(get("/status").session(session))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedin")))
                .andExpect(content().json("{'roles':'subscriber','user':'alex','status':'loggedin'}"));
                
        mockMvc.perform(get("/journal/subscribed").session(session)).andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test(retryAnalyzer = RetryTest.class)
    public void accessToSubscribedJournalsAsPublisher() throws Exception {
        LOGGER.info("running accessToSubscribedJournalsAsPublisher  changed again and again");
        MockHttpSession session = getLoginSession("peter", "123abc");
        
        mockMvc.perform(get("/status").session(session))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("loggedin")))
                .andExpect(content().json("{'roles':'publisher','user':'peter','status':'loggedin'}"));
                
        mockMvc.perform(get("/journal/subscribed").session(session)).andDo(print())
                .andExpect(status().isOk());
    }
}
