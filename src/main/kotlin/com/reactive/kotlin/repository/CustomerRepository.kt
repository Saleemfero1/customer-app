package com.reactive.kotlin.repository

import com.reactive.kotlin.model.Customer
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerRepository:ReactiveCrudRepository<Customer,Long> {

    fun findByEmailId(emailId: String): Mono<Customer>

    fun findAllByStatus(status: String):Flux<Customer>

    @Query("SELECT * FROM customers")
    fun getAllCustomers(): Flux<Customer>

    @Query("SELECT * FROM customers WHERE customer_key = ?")
    fun getCustomerByCustomerKey(customerKey:String): Mono<Customer>

    @Query("SELECT * FROM customers WHERE email_id = ?")
    fun getCustomerByEmailId(emailId:String):Mono<Customer>

    @Query("SELECT * FROM customers WHERE status = ?")
    fun getCustomersByStatus(status:String):Flux<Customer>

    @Query("INSERT INTO customers (customer_id, " +
            "first_name, " +
            "last_name, " +
            "address_line_one, " +
            "address_line_two, " +
            "city, " +
            "state, " +
            "zipcode, " +
            "country, " +
            "phone_number, " +
            "email_id, status) " +
            "VALUES (" +
            ":#{#customer.customerId}, " +
            ":#{#customer.firstName}, " +
            ":#{#customer.lastName}, " +
            ":#{#customer.addressLineOne}, " +
            ":#{#customer.addressLineTwo}, " +
            ":#{#customer.city}, " +
            ":#{#customer.state}, " +
            ":#{#customer.zipcode}, " +
            ":#{#customer.country}, " +
            ":#{#customer.phoneNumber}, " +
            ":#{#customer.emailId}, " +
            ":#{#customer.status})")
    fun addCustomer(@Param("customer") customer: Customer): Mono<Customer>

    @Query("DELETE FROM customers WHERE customer_key = ?")
    fun deleteCustomerByCustomerKey(customerKey:Long):Mono<Void>


}