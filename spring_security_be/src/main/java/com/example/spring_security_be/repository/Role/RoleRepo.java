package com.example.spring_security_be.repository.Role;

import com.example.spring_security_be.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
