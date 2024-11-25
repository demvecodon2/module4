package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.reporitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;



    private User loggedInUser;

    @Override
    public boolean login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        loggedInUser = null;
    }

    @Override
    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }


    @Override
    public boolean isUsernameTaken(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }


    @Override
    public void registerUser(User user) {

        userRepository.save(user);
    }
}
