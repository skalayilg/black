package com.sancho.journal.web.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sancho.journal.web.model.Topic;
import com.sancho.journal.web.model.User;

@Transactional(readOnly = true)
public interface TopicRepository extends CrudRepository<Topic, Long> {
    
    Collection<Topic> findBySubscribers(User subscribers);
    
}
