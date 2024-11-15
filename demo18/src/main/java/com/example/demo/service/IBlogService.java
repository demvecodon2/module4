package com.example.demo.service;

import com.example.demo.model.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogService {

    Blog createBlog(Blog blog);

    List<Blog> getAllBlogs();

    Optional<Blog> getBlogById(Long id);

    Blog updateBlog(Long id, Blog blogDetails);

    void deleteBlog(Long id);
}
