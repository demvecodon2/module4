package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/submit")
    public String submitForm(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        userService.saveUser(user);
        model.addAttribute("message", "User registration successful!");
        return "result";
    }
}
