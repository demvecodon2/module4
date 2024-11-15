package com.example.demo16.controller;

import com.example.demo16.model.Product;
import com.example.demo16.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/products")
public class ProductWebController {

    private final IProductService productService;

    @Autowired
    public ProductWebController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "list";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "details";
        } else {
            model.addAttribute("error", "Product not found");
            return "redirect:/products";
        }
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "addForm";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.add(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "editForm";
        } else {
            model.addAttribute("error", "Product not found");
            return "redirect:/products";
        }
    }
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        productService.update(id, product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
    @GetMapping("/search")
    public String findByName(@RequestParam String name, Model model) {
        List<Product> products = productService.findByName(name);
        if (products.isEmpty()) {
            model.addAttribute("message", "No products found matching the name '" + name + "'");
        }
        model.addAttribute("products", products);
        return "list";
    }
}
