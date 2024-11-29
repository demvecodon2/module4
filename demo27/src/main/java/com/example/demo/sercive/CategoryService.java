package com.example.demo.sercive;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public void updateCategory(Long id, Category categoryDetails) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(categoryDetails.getName());
            categoryRepository.save(updatedCategory);
        } else {
            throw new RuntimeException("Category with ID " + id + " not found");
        }

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được trống.");
        }
        return categoryRepository.save(category);
    }
}