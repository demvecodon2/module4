package com.example.demo11.repository;

import com.example.demo11.model.Login;
import com.example.demo11.model.User;

import java.util.List;

public interface IUserRepository {
    User checkLogin(Login login);

}
