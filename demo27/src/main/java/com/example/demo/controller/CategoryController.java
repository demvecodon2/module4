package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.sercive.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category_list";
    }

    @GetMapping("/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "create_category";
    }

    @PostMapping
    public String createCategory(@Validated @ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_category";
        }

        try {
            if (category.getName() == null || category.getName().trim().isEmpty()) {
                model.addAttribute("error", "Tên danh mục không được để trống.");
                return "create_category";
            }
            categoryService.createCategory(category);
            model.addAttribute("success", "Danh mục đã được tạo thành công.");
            return "redirect:/categories";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Danh mục đã tồn tại.");
            return "create_category";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "create_category";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tạo danh mục: " + e.getMessage());
            return "create_category";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        var category = categoryService.getCategoryById(id);
        if (category.isEmpty()) {
            model.addAttribute("error", "Danh mục không tồn tại.");
            return "error_page";
        }
        model.addAttribute("category", category.get());
        return "edit_category";
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable Long id, @Validated @ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_category";
        }
        try {
            categoryService.updateCategory(id, category);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "edit_category";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật danh mục: " + e.getMessage());
            return "edit_category";
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.deleteCategory(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "error_page";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa danh mục: " + e.getMessage());
            return "error_page";
        }
        return "redirect:/categories";
    }
}
