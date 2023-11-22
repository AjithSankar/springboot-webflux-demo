package com.reactive.springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebFluxTest {

    @Test
    public void testMono() {
        Mono<?> stringMono = Mono.just("Ajith")
                .then(Mono.error(new RuntimeException("")))
                .log();
        stringMono.subscribe(System.out::println);
    }

    @Test
    public void testFlux() {
        var stringFlux = Flux.just("Spring", "Spring boot", "Reactive", "Java")
                .concatWithValues("aws")
                .concatWith(Flux.error(new RuntimeException("exception occurred")))
                .concatWithValues("ec2")
                .log();

        stringFlux.subscribe(System.out::println, e -> e.getMessage());
    }
}
