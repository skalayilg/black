package com.sancho.journal.web.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sancho.journal.web.model.Journal;
import com.sancho.journal.web.model.User;

@Transactional(readOnly = true)
public interface JournalRepository extends CrudRepository<Journal, Long> {
    
    List<Journal> findByUser(User user);
    
    List<Journal> findByTopicSubscribers(User user);
    
}
