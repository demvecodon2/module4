package com.example.demo_spring_data.repository;

import com.example.demo_spring_data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
