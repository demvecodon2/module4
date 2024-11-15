package com.example.demo_spring_data.repository;


import com.example.demo_spring_data.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
