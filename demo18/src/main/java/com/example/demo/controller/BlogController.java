package com.example.demo.controller;


import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String getAllBlogs(Model model) {
        List<Blog> blogList = blogService.getAllBlogs();
        model.addAttribute("blogList", blogList);
        return "blog_list";
    }

    @GetMapping("/{id}")
    public String getBlogById(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        model.addAttribute("blog", blog);
        return "blog_detail";
    }

    @GetMapping("/add")
    public String addBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog_form";
    }

    @GetMapping("/update/{id}")
    public String updateBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        model.addAttribute("blog", blog);
        return "blog_form";
    }

    @PostMapping("/save")
    public String saveOrUpdateBlog(@ModelAttribute Blog blog) {
        if (blog.getId() == null) {
            blogService.createBlog(blog);
        } else {
            blogService.updateBlog(blog.getId(), blog);
        }
        return "redirect:/blog";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blog";
    }
}
