package com.videorentalapi.service.controllers;

import com.videorentalapi.service.models.User;
import com.videorentalapi.service.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.videorentalapi.service.exception.DuplicateResourceException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) throws DuplicateResourceException{
        String username = user.getUsername();
        String password = user.getPassword();
        user.setUsername(username);
        user.setPassword(password);
        User  existingUser = userService.findByUsername(user.getUsername());
        if(existingUser == null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(username, password));
        }
        throw new DuplicateResourceException("user with"+username+"already exist");
    }
}
