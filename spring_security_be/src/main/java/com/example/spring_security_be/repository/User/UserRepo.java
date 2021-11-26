package com.example.spring_security_be.repository.User;

import com.example.spring_security_be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> ,MyUserRepo{}
