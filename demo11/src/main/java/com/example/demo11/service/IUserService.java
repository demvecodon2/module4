package com.example.demo11.service;

import com.example.demo11.model.Login;
import com.example.demo11.model.User;

public interface IUserService {
    User checkLogin(Login login);

}
