package com.example.demo16.service;

import com.example.demo16.model.Product;
import com.example.demo16.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }
    @Override
    public void add(Product product) {
        productRepository.add(product);
    }
    @Override
    public void update(int id, Product product) {
        Product existingProduct = productRepository.getById(id);
        if (existingProduct != null) {
            productRepository.update(id, product);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found.");
        }
    }
    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }
    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
}
