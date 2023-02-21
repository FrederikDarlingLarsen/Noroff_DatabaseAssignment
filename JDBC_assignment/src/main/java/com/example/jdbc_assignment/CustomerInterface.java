package com.example.jdbc_assignment;

import java.util.List;

public interface CustomerInterface extends CRUDRepository<Customer, Integer> {


    List<Customer> findAll();
    Customer findById(Integer id);
    int insert(Customer object);
    int update(Customer object);
    int delete(Customer object);
    int deleteById(Integer id);
}