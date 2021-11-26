package com.example.spring_security_be.repository.User;

import com.example.spring_security_be.entities.User;

public interface MyUserRepo {
    boolean isExistedByUsername(String username);
    User findByUsername(String username);
}
