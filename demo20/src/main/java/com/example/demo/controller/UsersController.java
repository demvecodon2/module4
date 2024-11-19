package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @Autowired
    private IUsersService userService;
    @GetMapping("/users")
    public String showUserForm(Model model) {
        model.addAttribute("users", new Users());
        return "index";
    }
    @PostMapping("/submit")
    public String submitForm(@Validated Users users, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        userService.saveUser(users);
        model.addAttribute("user", users);
        return "result";
    }
}
