//package com.example.demo_spring_data.service.iml;
//
//
//import com.example.demo_spring_data.model.Category;
//import com.example.demo_spring_data.repository.CatogoryRepository;
//import com.example.demo_spring_data.service.ICatagorySevice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
//@Service
//public class CatagoryService implements ICatagorySevice {
//    @Autowired
//    private CatogoryRepository catogoryRepository;
//
//    @Override
//    public void addCategory(String category) {
//        Category catogory = new Category();
//        catogory.setName(category);
//        catogoryRepository.save(catogory);
//
//    }
//
//    @Override
//    public void deleteCategory(Long id) {
//        catogoryRepository.deleteById(id);
//
//
//    }
//
//    @Override
//    public void updateCategory(Long id, String newCategory) {
//        Category catogory = catogoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
//        catogory.setName(newCategory);
//        catogoryRepository.save(catogory);
//
//    }
//
//    @Override
//    public Iterable<String> getAllCategories() {
//        return catogoryRepository.findAll().stream().map(Category::getName).collect(Collectors.toList());
//    }
//}
