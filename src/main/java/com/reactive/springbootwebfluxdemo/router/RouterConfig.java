package com.reactive.springbootwebfluxdemo.router;

import com.reactive.springbootwebfluxdemo.handler.CustomerHandler;
import com.reactive.springbootwebfluxdemo.handler.CustomerStreamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private CustomerHandler customerHandler;
    private CustomerStreamHandler customerStreamHandler;

    public RouterConfig(CustomerHandler customerHandler, CustomerStreamHandler customerStreamHandler) {
        this.customerHandler = customerHandler;
        this.customerStreamHandler = customerStreamHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/router/customers", customerHandler::loadCustomers)
                .GET("/router/customers/stream", customerStreamHandler::loadCustomers)
                .GET("/router/customer/{input}",customerHandler::findCustomer)
                .POST("/router/customers",customerHandler::saveCustomer)
                .build();
    }
}
