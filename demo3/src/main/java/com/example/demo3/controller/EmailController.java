package com.example.demo3.controller;

import com.example.demo3.model.Email;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EmailController {
    private Email email = new Email();

    @ModelAttribute("mailConfig")
    public Email getEmail() {
        return email;
    }

    @GetMapping("/")
    public String getMailConfig(Model model) {
        return "home";
    }

    @PostMapping("/saveConfig")
    public String saveMailConfig(@ModelAttribute Email email, Model model, String action) {
        this.email = email;

        if ("save".equals(action)) {
            model.addAttribute("message", "Email configuration saved successfully!");
        } else if ("update".equals(action)) {
            model.addAttribute("message", "Email configuration updated successfully!");
        } else {
            model.addAttribute("message", "Unknown action.");
        }
        return "home";
    }
}
