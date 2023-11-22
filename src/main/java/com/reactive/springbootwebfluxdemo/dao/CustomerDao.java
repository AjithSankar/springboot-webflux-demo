package com.reactive.springbootwebfluxdemo.dao;

import com.reactive.springbootwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public static void sleep(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomers() {

        return IntStream.rangeClosed(1,10)
                .peek(CustomerDao::sleep)
                .peek(i -> System.out.println("Processing count " + i))
                .mapToObj(i -> new Customer(i , "Customer"+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getAllCustomersStream() {

        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing stream of events : " + i))
                .map(i -> new Customer(i , "Customer"+i));
    }

    public Flux<Customer> getCustomers_route() {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("Processing count in stream flow " + i))
                .map(i -> new Customer(i,"Customer"+i));
    }

    public Flux<Customer> getCustomersStream_route() {
        return Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing count in stream flow " + i))
                .map(i -> new Customer(i,"Customer"+i));
    }
}
