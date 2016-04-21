package com.sancho.journal.web.mvc;

import static com.sancho.test.ITestSpringUtil.mockPrincipal;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertNotNull;

import org.springframework.http.MediaType;
import org.testng.annotations.Test;

public class JournalControllerIT extends ControllerIT {
    
    @Test
    public void allJournal() throws Exception {
        
        String jsonexp = "[{'id':1,'topic':'Medical','title':'Cold Spring Harbor Perspectives in Medicine','file':'abc1.pdf','publisher':'Peter Matt'},"
                + "{'id':2},{'id':3},{'id':4},{'id':5},{'id':6}]";
                
        assertNotNull(mockMvc);
        mockMvc.perform(get("/journal")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(content().json(jsonexp));
                
    }
    
    @Test
    public void publishedJournalByUser() throws Exception {
        String jsonexp = "[{'id':1,'topic':'Medical','title':'Cold Spring Harbor Perspectives in Medicine','file':'abc1.pdf','publisher':'Peter Matt'},"
                + "{'id':2},{'id':3}]";
                
        assertNotNull(mockMvc);
        
        mockMvc.perform(
                get("/journal/published")
                        .principal(mockPrincipal("peter", "publisher")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().json(jsonexp));
    }
    
    @Test
    public void subscribedJournalByUser() throws Exception {
        String jsonexp = "[{'id':1,'topic':'Medical','title':'Cold Spring Harbor Perspectives in Medicine','file':'abc1.pdf','publisher':'Peter Matt'},"
                + "{'id':2},{'id':3},{'id':4}]";
                
        assertNotNull(mockMvc);
        
        mockMvc.perform(
                get("/journal/subscribed")
                        .principal(mockPrincipal("alex", "subscriber")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(content().json(jsonexp));
    }
}
