package com.example.demo_spring_data.service.iml;

import com.example.demo_spring_data.model.Category;
import com.example.demo_spring_data.repository.CategoryRepository;

import com.example.demo_spring_data.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Override
    public void updateCategory(Long id, Category category) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Danh mục không tồn tại với id: " + id);
        }
        category.setId(id);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Danh mục không tồn tại với id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
