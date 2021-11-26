package com.example.spring_security_be.service.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.spring_security_be.entities.Role;
import com.example.spring_security_be.entities.User;
import com.example.spring_security_be.repository.User.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found !");
            throw new UsernameNotFoundException("User not found !");
        }
        log.info("User Service");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().stream().forEach(role -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(simpleGrantedAuthority);
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public boolean validateUser(User user) {
        if (user.getUsername().length() == 0
                || user.getPassword().length() == 0
                || user.getRoles().isEmpty()
                || user.getEmail().length() == 0
                || userRepo.isExistedByUsername(user.getUsername())) {
            log.info("False");
            return false;
        }
        log.info("True");
        return true;
    }

    @Override
    public User findUserByToken(String token) {
        if(token.length() != 0){
            Algorithm algorithm = Algorithm.HMAC256("ngoctin".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            User user = userRepo.findByUsername(username);
            if(user != null){
                return user;
            }
            return null;
        }
        return null;
    }
}
