package com.example.demo_spring_data.service;

import com.example.demo_spring_data.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService {

    void createBlog(Blog blog);
    Page<Blog> getAllBlogs(Pageable pageable);
    Optional<Blog> getBlogById(Long id);
    void updateBlog(Long id, Blog blogDetails);
    void deleteBlog(Long id);
    Page<Blog> getBlogsByCategory(Long categoryId, Pageable pageable);
    Page<Blog> getAllBlogsSorted(Pageable pageable);
    Page<Blog> searchBlogsByTitle(String title, Pageable pageable);
}
