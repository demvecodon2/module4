package com.example.demo.service;

import com.example.demo.model.User;

public interface IUserService {

    boolean login(String username, String password);

    void logout();

    boolean isUserLoggedIn();

    boolean isUsernameTaken(String username);

    void registerUser(User user);
}
