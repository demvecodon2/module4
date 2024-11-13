package com.example.demo13.repository;

import com.example.demo13.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product getById(int id);
    void add(Product product);
    void update(int id, Product product);
    void delete(int id);
    List<Product> findByName(String name);

}
