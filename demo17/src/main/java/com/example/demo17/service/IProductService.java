package com.example.demo17.service;



import com.example.demo17.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product getById(int id);
    void add(Product product);
    void update(int id, Product product);
    void delete(int id);
    List<Product> findByName(String name);
}
