package com.example.demo2.repository;

import com.example.demo2.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class CustomerRepository implements  ICustomerRepository{
    public static List<Customer> customers = new ArrayList<Customer>();
    static {
        customers.add(new Customer(1, "Nguyen Khac Nhat", "nhat@gmail.com", "Hà Nội"));
        customers.add(new Customer(2, "Phan Van Duc", "duc@gmail.com", "Đà Nẵng "));
        customers.add(new Customer(3, "Phan Van Thanh", "thanh@gmail.com", "Huế"));
        customers.add(new Customer(4, "Phan Van Anh", "anh@gmail.com", "Sài Gòn"));
        customers.add(new Customer(5, "Code Gym", "Codegym@gmail.com", "Hồ Chí Minh"));


    }
    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }
    @Override
    public Customer getCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }


}
