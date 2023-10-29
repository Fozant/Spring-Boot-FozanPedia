package com.fauzan.springboot.springBootFauzan.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.web.dto.UserRegistration;

public interface UserService extends UserDetailsService {

	  User save(User user);

   

    User findByEmail(String email);
    
    User getUserById(long userId);
}
