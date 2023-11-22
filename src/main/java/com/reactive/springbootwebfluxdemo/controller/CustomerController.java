package com.reactive.springbootwebfluxdemo.controller;

import com.reactive.springbootwebfluxdemo.dao.CustomerDao;
import com.reactive.springbootwebfluxdemo.dto.Customer;
import com.reactive.springbootwebfluxdemo.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
       return customerService.getAllCustomers();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream() {
        return customerService.getAllCustomersStream();
    }
}
