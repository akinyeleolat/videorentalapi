package com.videorentalapi.service.services;


import com.videorentalapi.service.models.User;
import com.videorentalapi.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;


    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
//        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(String username, String password) {

        User user = new User();

        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username){
        User user = new User();
        user.setUsername(username);
        Optional<User> userDetails=userRepository.findByUsername(user.getUsername());
        return userDetails.orElse(null);
    }

}


