package com.sancho.journal.web.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long             id;
    @Column(unique = true)
    private String           name;
    @ManyToMany(mappedBy = "topicSubcriptions", targetEntity = User.class)
    private Collection<User> subscribers = new ArrayList<>();
    
    public Topic() {
        // Default Constructor required
    }
    
    public Topic(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    
    public Topic(String name) {
        super();
        this.name = name;
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
    
    public Collection<User> getSubscribers() {
        return subscribers;
    }
    
    public void setSubscribers(Collection<User> subscribers) {
        this.subscribers = subscribers;
    }
    
}
