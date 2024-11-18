package com.example.demo_spring_data.repository;

import com.example.demo_spring_data.model.Blog;
import com.example.demo_spring_data.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
   

    Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Blog> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Blog> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
