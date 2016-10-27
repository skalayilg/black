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

public class TopicControllerIT extends ControllerIT {
    
    @Test
    public void allTopics() throws Exception {
        String jsonexp = "[{'id':1,'name':'Medical'},{'id':2,'name':'Biology'},{'id':3,'name':'Cardiology'},"
                + "{'id':4,'name':'Endocrinology'},{'id':5,'name':'Infectious Diseases'},{'id':6,'name':'Genetics'},"
                + "{'id':7,'name':'Microbiology'},{'id':8,'name':'Neurology'},{'id':9,'name':'Oncology'},"
                + "{'id':10,'name':'Physiology'}]";
                
        assertNotNull(mockMvc);
        mockMvc.perform(get("/topic")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(content().json(jsonexp));
    }
    
    @Test
    public void subscribedJournalByUser() throws Exception {
        String jsonexp = "[{'id':1,'name':'Medical'},{'id':2,'name':'Biology'},{'id':3,'name':'Cardiology'}]";
        
        assertNotNull(mockMvc);
        mockMvc.perform(get("/topic/subscribed").principal(mockPrincipal("alex", "subscriber")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().json(jsonexp));
    }
}
