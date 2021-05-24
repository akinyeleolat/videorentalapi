package com.videorentalapi.service.security;

import com.videorentalapi.service.models.User;

/**
 * This interface defines the functions that are used to manage the security
 * configuration
 *
 */
public interface SecurityService {

    /**
     * Gets the current user's username
     * @return
     */
    public String getUsername();




    /**
     * Get the current user (if available)
     * @return
     */
    public User getUser();

}
