package com.example.demo.service;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(Users user) {
        userRepository.save(user);
    }
}
