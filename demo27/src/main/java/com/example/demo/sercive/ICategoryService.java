package com.example.demo.sercive;



import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    void deleteCategory(Long id);

    void updateCategory(Long id, Category categoryDetails);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    Category createCategory(Category category);
}
