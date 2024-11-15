package com.example.demo14.service;

import com.example.demo14.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void remove(int id);
    void save(Customer customer);
}
