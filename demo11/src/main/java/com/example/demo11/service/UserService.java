package com.codegym.service;

import com.codegym.model.Login;
import com.codegym.model.User;
import com.codegym.repository.UserRepository;
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

    // Kiểm tra đăng nhập người dùng
    public User checkLogin(Login login) {
        return userRepository.checkLogin(login);  // Gọi repository để kiểm tra đăng nhập
    }
}
