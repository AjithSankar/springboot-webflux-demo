package com.reactive.springbootwebfluxdemo.handler;

import com.reactive.springbootwebfluxdemo.dao.CustomerDao;
import com.reactive.springbootwebfluxdemo.dto.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    private CustomerDao customerDao;

    public CustomerStreamHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customers = customerDao.getCustomersStream_route();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customers , Customer.class);
    }
}
