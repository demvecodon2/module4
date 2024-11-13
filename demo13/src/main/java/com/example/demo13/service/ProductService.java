package com.example.demo13.service;

import com.example.demo13.model.Product;
import com.example.demo13.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;


    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        return iProductRepository.getById(id);
    }

    @Override
    public void add(Product product) {
        iProductRepository.add(product);

    }

    @Override
    public void update(int id, Product product) {
        Product oldProduct = iProductRepository.getById(id);
        if (oldProduct != null) {
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setManufacturer(product.getManufacturer());
        }

    }

    @Override
    public void delete(int id) {
        iProductRepository.delete(id);

    }

    @Override
    public List<Product> findByName(String name) {
        return iProductRepository.findByName(name);
    }


}
