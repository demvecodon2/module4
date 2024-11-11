package com.example.demo2.controller;

import com.example.demo2.model.Customer;
import com.example.demo2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "/customer/list";
    }

    @GetMapping("/customers/{id}")
    public String getCustomerById(@PathVariable("id") int customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "/customer/detail";
        } else {
            model.addAttribute("error", "Customer not found!");
            return "/customer/error";
        }

    }
}

