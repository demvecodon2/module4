package com.example.demo9;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCalculatorForm() {
        return "/index";
    }
    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operation") String operation,
                            Model model) {
        double result = 0;
        String operationType = "";

        switch (operation) {
            case "add":
                result = num1 + num2;
                operationType = "Cộng";
                break;
            case "subtract":
                result = num1 - num2;
                operationType = "Trừ";
                break;
            case "multiply":
                result = num1 * num2;
                operationType = "Nhân";
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = Double.NaN;
                }
                operationType = "Chia";
                break;
        }

        model.addAttribute("result", result);
        model.addAttribute("operation", operationType);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);

        return "/index";
    }
}