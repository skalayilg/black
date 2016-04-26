package com.sancho.journal.web.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancho.journal.web.dao.JournalRepository;
import com.sancho.journal.web.dao.TopicRepository;
import com.sancho.journal.web.dao.UserRepository;
import com.sancho.journal.web.model.Journal;
import com.sancho.journal.web.model.Topic;

@Service
public class JournalService {
    
    @Autowired
    JournalRepository journalRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    TopicRepository topicRepository;
    
    public List<com.sancho.journal.web.vo.Journal> findAllJounals() {
        Iterable<Journal> ji = journalRepository.findAll();// working
        List<com.sancho.journal.web.vo.Journal> jl = new ArrayList<>();
        for (Journal j : ji) {
            com.sancho.journal.web.vo.Journal vo = new com.sancho.journal.web.vo.Journal(j);
            jl.add(vo);
        }
        return jl;
    }
    
    public List<com.sancho.journal.web.vo.Journal> findPublishedJounalsByUser(String username) {
        Iterable<Journal> ji = journalRepository.findByUser(userRepository.findByUsername(username));
        List<com.sancho.journal.web.vo.Journal> jl = new ArrayList<>();
        for (Journal j : ji) {
            com.sancho.journal.web.vo.Journal vo = new com.sancho.journal.web.vo.Journal(j);
            jl.add(vo);
        }
        return jl;
    }
    
    public List<com.sancho.journal.web.vo.Journal> findsubscribedJounalsByUser(String username) {
        Iterable<Journal> ji = journalRepository.findByTopicSubscribers(userRepository.findByUsername(username));
        List<com.sancho.journal.web.vo.Journal> jl = new ArrayList<>();
        for (Journal j : ji) {
            com.sancho.journal.web.vo.Journal vo = new com.sancho.journal.web.vo.Journal(j);
            jl.add(vo);
        }
        return jl;
    }
    
    public List<com.sancho.journal.web.vo.Topic> findAllTopics() {
        Iterable<Topic> ti = topicRepository.findAll();
        List<com.sancho.journal.web.vo.Topic> tl = new ArrayList<>();
        for (Topic t : ti) {
            com.sancho.journal.web.vo.Topic vo = new com.sancho.journal.web.vo.Topic(t);
            tl.add(vo);
        }
        return tl;
    }
    
    public List<com.sancho.journal.web.vo.Topic> findSubscribedTopics(String username) {
        Iterable<Topic> ti = topicRepository.findBySubscribers(userRepository.findByUsername(username));
        List<com.sancho.journal.web.vo.Topic> tl = new ArrayList<>();
        for (Topic t : ti) {
            com.sancho.journal.web.vo.Topic vo = new com.sancho.journal.web.vo.Topic(t);
            tl.add(vo);
        }
        return tl;
    }
    
    public Long publishJournal(Long topicId, String title, String filename, String username) {
        Journal je = new Journal();
        je.setFile(filename);
        je.setTitle(title);
        je.setTopic(topicRepository.findOne(topicId));
        je.setUser(userRepository.findByUsername(username));
        je = journalRepository.save(je);
        return je.getId();
    }
    
    public String getPublishedJournalFile(Long id, String username) {
        Journal j = journalRepository.findByUserAndId(userRepository.findByUsername(username), id);
        if (j != null) {
            return j.getFile();
        } else {
            return null;
        }
    }
    
    public String getSubscribedJournalFile(Long id, String username) {
        
        Journal j = journalRepository.findByTopicSubscribersAndId(userRepository.findByUsername(username), id);
        if (j != null) {
            return j.getFile();
        } else {
            return null;
        }
    }
    
}
