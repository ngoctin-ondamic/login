package com.example.spring_security_be.controllers;

import com.example.spring_security_be.entities.User;
import com.example.spring_security_be.service.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/save")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.validateUser(user)) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You miss something !");
    }

    @GetMapping(path = "/get/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username){
        User user = userService.findUserByUsername(username);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !");
    }

    @GetMapping(path = "/get/byToken")
    public ResponseEntity<?> getUserByJwtToken(){
         // call user service
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Username : {}",username);
        User user = userService.findUserByUsername(username);

        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !");
    }

}
