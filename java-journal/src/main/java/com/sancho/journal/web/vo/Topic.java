package com.sancho.journal.web.vo;

public class Topic {
    
    private Long   id;
    private String name;
    
    public Topic() {
        // Default
    }
    
    public Topic(com.sancho.journal.web.model.Topic t) {
        
        id = t.getId();
        name = t.getName();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
