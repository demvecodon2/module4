package com.example.demo5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    @GetMapping("/dictionary")
    public String lookupWord(@RequestParam(value = "word", required = false) String word, Model model) {
        String definition = null;


        if (word != null) {
            switch (word.toLowerCase()) {
                case "java":
                    definition = "ngôn ngữ lập trình";
                    break;
                case "spring":
                    definition = "framework để xây dựng các ứng dụng java.";
                    break;
                default:
                    definition = "ko tìm thấy";
            }
        }

        model.addAttribute("word", word);
        model.addAttribute("definition", definition);
        return "/dictionary";
    }
}
