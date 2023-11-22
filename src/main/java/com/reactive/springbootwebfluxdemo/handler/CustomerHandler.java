package com.reactive.springbootwebfluxdemo.handler;


import com.reactive.springbootwebfluxdemo.dao.CustomerDao;
import com.reactive.springbootwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    private CustomerDao customerDao;

    public CustomerHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customers = customerDao.getCustomers_route();
        return ServerResponse.ok().body(customers , Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId= Integer.valueOf( request.pathVariable("input"));
        Mono<Customer> customerMono = customerDao.getCustomers_route().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono,Customer.class);
    }


    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }
}
