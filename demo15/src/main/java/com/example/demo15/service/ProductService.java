package com.example.demo15.service;

import com.example.demo15.model.Product;
import com.example.demo15.repository.IProductRepository;
import com.example.demo15.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
@Service
public class ProductService implements IProductService{
    private final IProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
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
        productRepository.update(id,product);

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
