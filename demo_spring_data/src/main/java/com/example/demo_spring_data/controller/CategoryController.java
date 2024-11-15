//package com.example.demo_spring_data.controller;
//
//import com.example.demo_spring_data.service.ICatagorySevice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/category")
//public class CategoryController {
//
//    @Autowired
//    private ICatagorySevice categoryService;
//
//    @GetMapping("/list")
//    public String listCategories() {
//        return "list_categories";
//    }
//
//
//    @GetMapping("/add")
//    public String addCategory() {
//        return "add_category";
//    }
//
//    @PostMapping("/add")
//    public String saveCategory() {
//        return "redirect:/category/list";
//    }
//
//    @GetMapping("/edit")
//    public String editCategory() {
//        return "edit_category";
//    }
//
//    @PostMapping("/edit")
//    public String updateCategory() {
//        return "redirect:/category/list";
//    }
//
//    @GetMapping("/delete")
//    public String deleteCategory() {
//
//        return "redirect:/category/list";
//    }
//}
