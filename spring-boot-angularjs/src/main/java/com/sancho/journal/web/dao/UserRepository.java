package com.sancho.journal.web.dao;

import org.springframework.data.repository.CrudRepository;

import com.sancho.journal.web.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByUsername(String username);
    
}
