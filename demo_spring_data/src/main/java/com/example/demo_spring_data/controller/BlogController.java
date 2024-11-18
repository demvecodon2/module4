package com.example.demo_spring_data.controller;

import com.example.demo_spring_data.model.Blog;
import com.example.demo_spring_data.service.IBlogService;
import com.example.demo_spring_data.service.iml.BlogService;
import com.example.demo_spring_data.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String getAllBlogs(Pageable pageable, Model model) {
        Page<Blog> blogs = (Page<Blog>) blogService.getAllBlogs(pageable);
        model.addAttribute("blogs", blogs);
        return "blog_list";
    }

    @GetMapping("/{id}")
    public String getBlogById(@PathVariable Long id, Model model) {
        Optional<Blog> blogOptional = blogService.getBlogById(id);
        if (blogOptional.isEmpty()) {
            model.addAttribute("error", "Blog not found");
            return "error_page";
        }
        Blog blog = blogOptional.get();
        model.addAttribute("blog", blog);
        model.addAttribute("categoryName", blog.getCategory() != null ? blog.getCategory().getName() : "Chưa phân loại");
        return "blog_detail";
    }

    @GetMapping("/create")
    public String createBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "create_blog";
    }

    @PostMapping
    public String createBlog(@ModelAttribute Blog blog) {
        blogService.createBlog(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String editBlogForm(@PathVariable Long id, Model model) {
        Optional<Blog> blogOptional = blogService.getBlogById(id);
        if (blogOptional.isEmpty()) {
            model.addAttribute("error", "Blog not found");
            return "error_page";
        }
        model.addAttribute("blog", blogOptional.get());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit_blog";
    }

    @PostMapping("/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog) {
        blogService.updateBlog(id, blog);
        return "redirect:/blogs/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }

    @GetMapping("/sorted")
    public String getAllBlogsSorted(Pageable pageable, Model model) {
        Page<Blog> blogs = blogService.getAllBlogsSorted(pageable);
        model.addAttribute("blogs", blogs);
        return "blog_list";
    }


    @GetMapping("/search")
    public String searchBlogs(@RequestParam("title") String title, Pageable pageable, Model model) {
        Page<Blog> blogs = blogService.searchBlogsByTitle(title, pageable);
        model.addAttribute("blogs", blogs);
        return "blog_list";
    }


    @GetMapping("/category/{categoryId}")
    public String getBlogsByCategory(@PathVariable Long categoryId, Pageable pageable, Model model) {
        Page<Blog> blogs = blogService.getBlogsByCategory(categoryId, pageable);
        model.addAttribute("blogs", blogs);
        return "blog_list";
    }
}
