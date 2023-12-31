package com.reactive.kotlin.router

import com.reactive.kotlin.handler.CustomerHandler
import com.reactive.kotlin.handler.OrderHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class Router(private val customerHandler: CustomerHandler
             , private val orderHandler: OrderHandler) {

    @Bean
    fun customersRouter():RouterFunction<ServerResponse>{
        return route()
            .GET("/customer"){request -> customerHandler.getCustomers(request)}
            .POST("/customer") {request -> customerHandler.addCustomer(request)}
            .GET("/customer/{customerKey}"){request -> customerHandler.findCustomerById(request)}
            .DELETE("/customer/{customerKey}"){request -> customerHandler.deleteCustomer(request)}
            .PUT("/customer/{customerKey}"){ request -> customerHandler.updateCustomer(request) }
            .GET("/customer/v1/{emailId}"){request -> customerHandler.findCustomerByEmailId(request)}
            .GET("/customer/v2/{status}"){request -> customerHandler.findCustomersByStatus(request)}
            .POST("/order"){request -> orderHandler.createOrder(request)}


            .build()
    }
}