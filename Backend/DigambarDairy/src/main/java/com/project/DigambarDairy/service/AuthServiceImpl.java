package com.project.DigambarDairy.service;

import com.project.DigambarDairy.dao.UserRepository;
import com.project.DigambarDairy.model.User;
import com.project.DigambarDairy.util.EmailValidatorUtil;
import com.project.DigambarDairy.config.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;   // ✅ consistent name
    private final JwtUtil jwtUtil;
    private final TokenBlacklistService blacklist;

    public AuthServiceImpl(UserRepository repo,
                           PasswordEncoder passwordEncoder,   // ✅ match
                           JwtUtil jwtUtil,
                           TokenBlacklistService blacklist) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;  // ✅ match
        this.jwtUtil = jwtUtil;
        this.blacklist = blacklist;
    }

    @Override
    public User register(User user) {
        if (!EmailValidatorUtil.isValid(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (repo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (repo.existsByMobileNumber(user.getMobileNumber())) {
            throw new IllegalArgumentException("Mobile number already registered");
        }

        // ✅ encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(null);

        return repo.save(user);
    }

    @Override
    public String login(User userRequest) {
        User user;

        if (userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()) {
            user = repo.findByEmail(userRequest.getEmail())
                       .orElseThrow(() -> new RuntimeException("User not found with email"));
        } else if (userRequest.getMobileNumber() != null && !userRequest.getMobileNumber().isEmpty()) {
            user = repo.findByMobileNumber(userRequest.getMobileNumber())
                       .orElseThrow(() -> new RuntimeException("User not found with mobile number"));
        } else {
            throw new RuntimeException("Either email or mobile number must be provided");
        }

        // ✅ check encoded password
        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ generate JWT with email + role
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    @Override
    public void logout(String token) {
        blacklist.blacklistToken(token);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
