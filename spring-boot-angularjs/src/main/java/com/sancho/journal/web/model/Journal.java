package com.sancho.journal.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Journal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Topic  topic;
    private String title;
    private String file;
    @ManyToOne
    private User   user;
    
    public Journal() {
        // Default Constructor required
    }
    
    public Journal(Topic topic, String title, String file, User user) {
        this.topic = topic;
        this.title = title;
        this.file = file;
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }
    
    public Topic getTopic() {
        return topic;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getFile() {
        return file;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
}
