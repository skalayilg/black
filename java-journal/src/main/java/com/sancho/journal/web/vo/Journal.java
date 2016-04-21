package com.sancho.journal.web.vo;

public class Journal {
    
    private Long   id;
    private String topic;
    private String title;
    private String file;
    private String publisher;
    
    public Journal() {
        // Default
    }
    
    public Journal(com.sancho.journal.web.model.Journal je) {
        id = je.getId();
        topic = je.getTopic().getName();
        title = je.getTitle();
        file = je.getFile();
        publisher = je.getUser().getFullName();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getFile() {
        return file;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
