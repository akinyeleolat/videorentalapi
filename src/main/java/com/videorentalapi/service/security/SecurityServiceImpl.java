package com.videorentalapi.service.security;

import com.videorentalapi.service.services.UserService;
import com.videorentalapi.service.models.User;
import com.videorentalapi.service.services.MyUserDetailsService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{

    private MyUserDetailsService userDetailsService;


    private UserService userService;


    public SecurityServiceImpl(MyUserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService=userDetailsService;
        this.userService=userService;
    }

    @Override
    public String getUsername() throws UsernameNotFoundException {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        throw new UsernameNotFoundException("User not found");
    }


    @Override
    public User getUser() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.findByUsername(currentUserName);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
