package com.example.demo7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SandwichController {
    @GetMapping("/selectSpices")
    public String showSandwich(Model model) {
        List<String> spicesList = Arrays.asList("Muối", "Tiêu", "Mù tạt", "Ớt", "Tỏi");
        model.addAttribute("spicesList", spicesList);
        return "/selectSpices";
    }
    @PostMapping("/selectSpices")
    public String submitForm(HttpServletRequest request, Model model) {
        String[] selectedSpices = request.getParameterValues("spices");
        if (selectedSpices != null) {
            model.addAttribute("selectedSpices", Arrays.asList(selectedSpices));
        } else {
            model.addAttribute("selectedSpices", new ArrayList<>());
        }

        return "/result";
    }
}

