package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("user") User user, Model model) {

        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            model.addAttribute("message", "Login successful.");
            return "redirect:/books";
        }
        model.addAttribute("message", "Login failed. Try again.");
        return "books";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/login";
    }
}
