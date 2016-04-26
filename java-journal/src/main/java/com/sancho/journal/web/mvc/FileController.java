package com.sancho.journal.web.mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class FileController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    @Autowired
    ServletContext              context;
    
    @Autowired
    JournalService journalService;
    
    @RequestMapping(value = "/published", produces = "application/pdf") // headers = { "Content-disposition",
                                                                        // "attachment; filename=filename.pdf" }
    @ResponseBody
    public FileSystemResource getPublishedFile(@RequestParam("id") Long id, HttpServletRequest request) {
        String fileName = journalService.getPublishedJournalFile(id, request.getUserPrincipal().getName());
        if (fileName != null) {
            String abspath = context.getRealPath("./WEB-INF/upload/") + fileName;
            LOGGER.info(abspath);
            return new FileSystemResource(abspath);
        } else {
            return null;
        }
        
    }
    
    @RequestMapping(value = "/subscribed", produces = "application/pdf") // headers = { "Content-disposition",
    // "attachment; filename=filename.pdf" }
    @ResponseBody
    public FileSystemResource getSubscribedFile(@RequestParam("id") Long id, HttpServletRequest request) {
        String fileName = journalService.getSubscribedJournalFile(id, request.getUserPrincipal().getName());
        if (fileName != null) {
            String abspath = context.getRealPath("./WEB-INF/upload/") + fileName;
            LOGGER.info(abspath);
            return new FileSystemResource(abspath);
        } else {
            return null;
        }
        
    }
}
