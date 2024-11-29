package com.example.demo.repository;

import com.example.demo.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    Page<Blog> findByTitleContainingIgnoreCase(Long categoryId, Pageable pageable);


    Page<Blog> findBySearchNameContainingIgnoreCase(String searchName, Pageable pageable);

    Page<Blog> findByCategoryIdAndTitleContaining(Long categoryId, String searchName, Pageable pageable);

}
