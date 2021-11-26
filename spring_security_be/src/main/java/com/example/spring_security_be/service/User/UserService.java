package com.example.spring_security_be.service.User;

import com.example.spring_security_be.entities.User;

public interface UserService {

    User findUserByUsername(String username);
    User createUser(User user);
    boolean validateUser(User user);
    User findUserByToken(String token);
}
