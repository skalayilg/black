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
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;
    @Column(unique = true)
    private String username;
    private String password;
    private String fullName;
    private String role;
    
    @ManyToMany(targetEntity = Topic.class)
    private Collection<Topic> topicSubcriptions = new ArrayList<>();
    
    public User() {
        // Default Constructor required
    }
    
    public User(String username, String password, String fullName, String role) {
        super();
        
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void addTopic(Topic topic) {
        
        topicSubcriptions.add(topic);
    }
    
    public Collection<Topic> getTopicSubcriptions() {
        return topicSubcriptions;
    }
    
}
