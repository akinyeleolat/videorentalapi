package com.videorentalapi.service.controllers;

import com.videorentalapi.service.models.User;
import com.videorentalapi.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.videorentalapi.service.exception.DuplicateResourceException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users")
@Slf4j
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Object> createUser(@Valid @RequestBody User userInput) throws DuplicateResourceException{
        User newUser = new User();
        newUser.setUsername(userInput.getUsername());
        newUser.setPassword( userInput.getPassword());
        User  existingUser = userService.findByUsername(newUser.getUsername());
        if(existingUser != null){
            throw new DuplicateResourceException("user with "+newUser.getUsername()+" already exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(newUser.getUsername(),newUser.getPassword()));
    }
}
