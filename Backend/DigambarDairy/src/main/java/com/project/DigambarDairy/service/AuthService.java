package com.project.DigambarDairy.service;

import com.project.DigambarDairy.model.User;
import java.util.List;

public interface AuthService {
    User register(User user);
    String login(User user);
    void logout(String token);
    List<User> getAllUsers();
}
