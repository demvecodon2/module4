package com.example.demo_spring_data.service;

import com.example.demo_spring_data.model.Blog;
import java.util.List;
import java.util.Optional;

public interface IBlogService {

    Blog createBlog(Blog blog);

    List<Blog> getAllBlogs();

    Optional<Blog> getBlogById(Long id);

    Blog updateBlog(Long id, Blog blogDetails);

    void deleteBlog(Long id);
}
