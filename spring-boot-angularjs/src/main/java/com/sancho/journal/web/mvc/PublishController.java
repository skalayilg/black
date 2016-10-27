package com.sancho.journal.web.mvc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
// @RequestMapping("/fileUpload")
public class PublishController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishController.class);
    
    @Autowired
    ServletContext context;
    
    @Autowired
    JournalService journalService;
    
    @RequestMapping(value = { "/fileUpload" }, method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File f = new File(name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
                LOGGER.info("uploading to file : " + f.getAbsolutePath());
                stream.write(bytes);
                stream.close();
                return "{status:'success',message:'You successfully uploaded " + name + "!'}";
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return "{status:'failed',message:'You failed to upload " + name + ". Error:  " + e.getMessage() + "'}";
            }
        } else {
            return "{status:'failed',message:'You failed to upload " + name + " because the file was empty.'}";
        }
    }
    
    @RequestMapping(value = { "/publish" }, method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, String> publishJournal(@RequestParam("fileTitle") String title,
            @RequestParam("file") MultipartFile ufile, @RequestParam("fileTopic") Long topicId,
            HttpServletRequest request) {
        Map<String, String> out = new HashMap<>();
        if (!ufile.isEmpty()) {
            if (!(ufile.getSize() > 1048576)) {
                try {
                    
                    byte[] bytes = ufile.getBytes();
                    File d = new File(context.getRealPath("/WEB-INF/upload"));
                    d.mkdir();
                    File f = File.createTempFile("jjournal", ufile.getOriginalFilename().replaceAll(" ", ""), d);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
                    LOGGER.info("uploading to file : " + f.getAbsolutePath());
                    stream.write(bytes);
                    stream.close();
                    out.put("status", "success");
                    out.put("message", "You successfully uploaded " + title + "!");
                    Long id = journalService.publishJournal(topicId, title, f.getName(),
                            request.getUserPrincipal().getName());
                    out.put("id", Long.toString(id));
                    LOGGER.info("new journal added.  id : " + id);
                    return out;
                    
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                    out.put("status", "failed");
                    out.put("message", "You failed to upload " + title + ". Error:  " + e.getMessage());
                    return out;
                }
            } else {
                out.put("status", "failed");
                out.put("message", "You failed to upload " + title + ". Error:  file size exeeds limit");
                return out;
            }
        } else {
            out.put("status", "failed");
            out.put("message", "You failed to upload " + title + " because the file was empty.");
            return out;
        }
    }
    
}
