package com.sancho.journal.web.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalController {
    
    @Autowired
    JournalService journalService;
    
    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<com.sancho.journal.web.vo.Journal> allJournal() {
        return journalService.findAllJounals();
    }
    
    @RequestMapping(value = { "/published", "/published/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<com.sancho.journal.web.vo.Journal> publishedJournalByUser(HttpServletRequest request) {
        
        return journalService.findPublishedJounalsByUser(request.getUserPrincipal().getName());
    }
    
    @RequestMapping(value = { "/subscribed", "/subscribed/" }, method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public List<com.sancho.journal.web.vo.Journal> subscribedJournalByUser(HttpServletRequest request) {
        
        return journalService.findsubscribedJounalsByUser(request.getUserPrincipal().getName());
    }
    
}
