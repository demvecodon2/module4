package com.example.demo10;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String showFrom(ModelMap model) {
        model.addAttribute("employee",new Employee());
        return "/employee/create";
    }
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String submit(@RequestAttribute("employee") Employee employee,ModelMap model) {
        model.addAttribute("name", employee.getName());
        model.addAttribute("contactNumber", employee.getContactNumber());
        model.addAttribute("id",employee.getId());
        return "/employee/info";
    }
}
