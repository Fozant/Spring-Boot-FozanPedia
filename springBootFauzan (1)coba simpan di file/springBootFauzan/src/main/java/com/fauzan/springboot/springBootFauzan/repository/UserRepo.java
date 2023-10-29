package com.fauzan.springboot.springBootFauzan.repository;

import com.fauzan.springboot.springBootFauzan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
