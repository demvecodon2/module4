package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Category;
import com.example.demo.sercive.IBlogService;
import com.example.demo.sercive.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String getAllBlogs(@RequestParam(required = false, defaultValue = "") String searchName,
                              @RequestParam(required = false) Long categoryId,
                              @PageableDefault(page = 0, size = 5, sort = "title") Pageable pageable,
                              Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());

        Page<Blog> blogs;

        if (categoryId != null) {
            if (!searchName.isEmpty()) {
                blogs = blogService.getBlogsByCategoryAndId(categoryId, searchName, pageable);
            } else {
                blogs = blogService.getBlogsByCategory(categoryId, pageable);
            }
        } else {
            blogs = blogService.getBlogsBySearchName(searchName, pageable);
        }

        Category selectedCategory = categoryId != null ?
                categoryService.getCategoryById(categoryId).orElse(null) : null;
        model.addAttribute("selectedCategory", selectedCategory);
        model.addAttribute("blogs", blogs);
        model.addAttribute("searchName", searchName);
        model.addAttribute("categoryId", categoryId);

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
    public String createBlog(@Validated @ModelAttribute Blog blog, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_blog";
        }
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
    public String updateBlog(@PathVariable Long id, @Validated @ModelAttribute Blog blog, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_blog"; // Return to form if validation fails
        }
        blogService.updateBlog(id, blog);
        return "redirect:/blogs/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }

    @GetMapping("/search")
    public String searchBlogs(@RequestParam("title") String title, Pageable pageable, Model model) {
        Page<Blog> blogs = blogService.searchBlogsByTitle(title, pageable);
        model.addAttribute("blogs", blogs);
        return "blog_list";
    }
}
