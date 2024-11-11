package com.example.demo2.service;

import com.example.demo2.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(int customerId);

}
