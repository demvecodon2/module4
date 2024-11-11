package com.example.demo5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    // Xử lý yêu cầu GET cho "/dictionary"
    @GetMapping("/dictionary")
    public String lookupWord(@RequestParam(value = "word", required = false) String word, Model model) {
        String definition = null;


        if (word != null) {
            switch (word.toLowerCase()) {
                case "java":
                    definition = "A high-level programming language.";
                    break;
                case "spring":
                    definition = "A framework for building Java-based applications.";
                    break;
                default:
                    definition = "No definition found.";
            }
        }

        model.addAttribute("word", word);
        model.addAttribute("definition", definition);
        return "/dictionary";
    }
}
