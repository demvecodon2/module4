package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;
@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error_404";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

    @PostMapping("/update-quantity")
    public String updateQuantity(@RequestParam Long productId, @RequestParam int quantity, @ModelAttribute Cart cart, RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productService.findById(productId);
        if (!productOptional.isPresent()) {
            return "redirect:/error_404";
        }
        Product product = productOptional.get();
        try {
            cart.updateProductQuantity(product, quantity);
        } catch (IllegalArgumentException e) {

            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/cart";
        }

        return "redirect:/cart";
    }



    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, @ModelAttribute Cart cart) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            cart.removeProduct(productOptional.get());
        }
        return "redirect:/cart";
    }
}
