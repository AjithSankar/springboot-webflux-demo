package com.reactive.springbootwebfluxdemo.service;

import com.reactive.springbootwebfluxdemo.dao.CustomerDao;
import com.reactive.springbootwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getAllCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total processing time : " + (end - start)/1000 + " seconds");
        return customers;
    }

    public Flux<Customer> getAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getAllCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total processing time : " + (end - start)/1000 + " seconds");
        return customers;
    }
}
