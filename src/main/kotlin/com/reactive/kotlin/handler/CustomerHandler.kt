package com.reactive.kotlin.handler

import com.reactive.kotlin.model.Customer
import com.reactive.kotlin.repository.CustomerRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
@Component
class CustomerHandler(
    private val customerRepository: CustomerRepository) {

    fun addCustomer(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Customer::class.java)
            .flatMap { customer ->
                val savedCustomer = customerRepository.save(customer)
                ServerResponse.ok().body(savedCustomer, Customer::class.java)
            }.switchIfEmpty(ServerResponse.badRequest().build())
    }

    fun getCustomers(request: ServerRequest): Mono<ServerResponse> {
        val getAllCustomers = customerRepository.findAll()
        return ServerResponse.ok().body(getAllCustomers, Customer::class.java)
    }

    fun findCustomerById(request: ServerRequest): Mono<ServerResponse> {
        val customerKey = request.pathVariable("customerKey").toLong()
        return customerRepository.findById(customerKey).flatMap { customer ->
            ServerResponse.ok().bodyValue(customer)
        }.switchIfEmpty(ServerResponse.notFound().build())
    }

    fun findCustomerByEmailId(request: ServerRequest):Mono<ServerResponse>{
        val emailId = request.pathVariable("emailId")
        return customerRepository.findByEmailId(emailId).flatMap { customer ->
            ServerResponse.ok().bodyValue(customer)
        }
            .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun findCustomersByStatus(request: ServerRequest):Mono<ServerResponse>{
        val status = request.pathVariable("status")
        return customerRepository.findAllByStatus(status)
            .collectList()
            .flatMap { customers ->
                Mono.justOrEmpty(customers.takeIf { it.isNotEmpty()})
                    .flatMap {ServerResponse.ok().bodyValue(customers) }
                    .switchIfEmpty(ServerResponse.notFound().build())
            }
    }

    fun deleteCustomer(request: ServerRequest): Mono<ServerResponse> {
        val customerKey = request.pathVariable("customerKey").toLong()
        return customerRepository.existsById(customerKey)
            .flatMap { exists ->
                Mono.justOrEmpty(exists)
                    .filter { it }
                    .flatMap {
                        customerRepository.deleteById(customerKey)
                            .then(ServerResponse.noContent().build())
                    }
                    .switchIfEmpty(ServerResponse.notFound().build())
            }
    }

    fun updateCustomer(request: ServerRequest): Mono<ServerResponse> {
        val customerKey = request.pathVariable("customerKey").toLong()

        return customerRepository.findById(customerKey)
            .flatMap { existingCustomer ->
                request.bodyToMono(Customer::class.java)
                    .flatMap { updatedCustomer ->
                        val mergedCustomer = existingCustomer.copy(
                            customerId = updatedCustomer.customerId,
                            firstName = updatedCustomer.firstName,
                            lastName = updatedCustomer.lastName,
                            addressLineOne = updatedCustomer.addressLineOne,
                            addressLineTwo = updatedCustomer.addressLineTwo,
                            city = updatedCustomer.city,
                            state = updatedCustomer.state,
                            zipcode = updatedCustomer.zipcode,
                            country = updatedCustomer.country,
                            phoneNumber = updatedCustomer.phoneNumber,
                            emailId = updatedCustomer.emailId,
                            status = updatedCustomer.status
                        )
                        customerRepository.save(mergedCustomer)
                            .flatMap {
                                ServerResponse.ok().bodyValue("Customer updated successfully")
                            }
                    }
            }
            .switchIfEmpty(ServerResponse.notFound().build())
    }


}
/*
 val newCustomer = Customer(
 "001",
 "CUS001",
 "Saleem",
 "Fero",
 "CMA",
 "Hostel",
 "Bengaluru",
 "Karnataka",
 "560004",
 "India",
 "9611890978",
 "Active")
 */