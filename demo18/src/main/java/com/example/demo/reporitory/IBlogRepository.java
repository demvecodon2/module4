package com.example.demo.reporitory;

import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository<Blog ,Integer> {


}
