package com.example.demo11.controller;

import com.example.demo11.model.Login;
import com.example.demo11.model.User;
import com.example.demo11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("login", new Login());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(Login login) {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.checkLogin(login);

        if (user != null) {
            modelAndView.setViewName("welcome");
            modelAndView.addObject("user", user);
        } else {
            modelAndView.setViewName("home");
            modelAndView.addObject("errorMessage", "Invalid account or password");
        }
        return modelAndView;
    }
}
