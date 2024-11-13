package com.example.demo13.controller;

import com.example.demo13.model.Product;
import com.example.demo13.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public String findAll(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Product> products;
        if (search != null && !search.isEmpty()) {
            products = productService.findByName(search);
            model.addAttribute("search", search);
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }

    @PostMapping("/products/add")
    public String saveProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty() || product.getPrice() <= 0) {
            return "redirect:/products/add?error=invalid";
        }
        try {
            productService.add(product);
        } catch (Exception e) {
            return "redirect:/products/add?error=true";
        }
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Product product = productService.getById(id);
        if (product == null) {
            return "redirect:/products?error=notfound";
        }
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProduct(@PathVariable("id") int id, Product product) {
        if (product.getName() == null || product.getName().isEmpty() || product.getPrice() <= 0) {
            return "redirect:/products/" + id + "/edit?error=invalid";
        }
        try {
            productService.update(id, product);
        } catch (Exception e) {
            return "redirect:/products/" + id + "/edit?error=true";
        }
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id) {
        try {
            productService.delete(id);
        } catch (Exception e) {
            return "redirect:/products?error=notfound";
        }
        return "redirect:/products";
    }
    @GetMapping("/products/{id}")
    public String viewProductDetail(@PathVariable("id") int id, Model model) {
        Product product = productService.getById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "view";
        } else {
            return "redirect:/products?error=notfound";
        }
    }
}
