package com.example.spring_security_be.repository.User;

import com.example.spring_security_be.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Slf4j
public class MyUserRepoImpl implements MyUserRepo{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        Query query = entityManager.createNativeQuery(sql,User.class);
        query.setParameter(1,username);
        try {
            User user = (User)query.getResultList().stream().findFirst().get();
            log.info("roles : {}", user.getRoles());
            return user;
        }catch (NoSuchElementException exception){
            log.error("My User Repo Exception");
            return null;
        }
    }

    @Override
    public boolean isExistedByUsername(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username =  ?";
            Query query = entityManager.createNativeQuery(sql,User.class);
            query.setParameter(1,username);
            if(query.getResultList().size() > 0){
                return true;
            }else{
                return false;
            }
        }catch (Exception exception){
            throw exception;
        }
    }
}
