package com.videorentalapi.service.services;

import com.videorentalapi.service.models.User;

/**
 * Created by oluwatosin akinyele
 */
public interface UserService {

    /**
     * Save User
     * @param username
     * @param password
     */
    public User saveUser(String username, String password);

    /**
     * find user by username
     * @param Username
     * @return
     */
    public User findByUsername(String Username);
}
