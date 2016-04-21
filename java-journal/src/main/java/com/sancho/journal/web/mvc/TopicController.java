package com.sancho.journal.web.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sancho.journal.web.vo.Topic;

@RestController
@RequestMapping("/topic")
public class TopicController {
    
    @Autowired
    JournalService journalService;
    
    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Topic> allTopics() {
        return journalService.findAllTopics();
    }
    
    @RequestMapping(value = { "/subscribed",
            "/subscribed/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<com.sancho.journal.web.vo.Topic> subscribedJournalByUser(HttpServletRequest request) {
        
        return journalService.findSubscribedTopics(request.getUserPrincipal().getName());
    }
    
}
