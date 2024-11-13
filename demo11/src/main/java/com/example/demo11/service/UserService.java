package com.example.demo11.service;

import com.example.demo11.model.Login;
import com.example.demo11.model.User;
import com.example.demo11.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkLogin(Login login) {
        return userRepository.checkLogin(login);
    }
}
