package com.example.demo15.service;

import com.example.demo15.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product getById(int id);
    void add(Product product);
    void update(int id, Product product);
    void delete(int id);
    List<Product> findByName(String name);
}
