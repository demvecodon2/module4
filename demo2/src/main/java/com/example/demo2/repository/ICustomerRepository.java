package com.example.demo2.repository;

import com.example.demo2.model.Customer;
import java.util.List;

public interface ICustomerRepository {

    List<Customer> getAllCustomers();

    Customer getCustomerById(int customerId);
}
