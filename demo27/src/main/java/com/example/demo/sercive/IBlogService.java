package com.example.demo.sercive;

import com.example.demo.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService {
    void createBlog(Blog blog);

    Optional<Blog> getBlogById(Long id);

    void updateBlog(Long id, Blog blogDetails);

    void deleteBlog(Long id);

    Page<Blog> getBlogsByCategory(Long categoryId, Pageable pageable);


    Page<Blog> getBlogsByCategoryAndId(Long categoryId, String searchName, Pageable pageable);


    Page<Blog> searchBlogsByTitle(String title, Pageable pageable);

    Page<Blog> getBlogsBySearchName(String searchName, Pageable pageable);
}
