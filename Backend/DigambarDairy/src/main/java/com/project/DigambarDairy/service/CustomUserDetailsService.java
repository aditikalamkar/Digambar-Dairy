package com.project.DigambarDairy.service;

import com.project.DigambarDairy.dao.UserRepository;
import com.project.DigambarDairy.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	  @Autowired
	    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User  user;

        // check if input is email or mobile
        if (username.contains("@")) {
            user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        } else {
            user = userRepository.findByMobileNumber(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile: " + username));
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // internally Spring Security needs a unique value
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
    
}
