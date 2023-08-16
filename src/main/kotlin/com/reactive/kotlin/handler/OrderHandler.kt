package com.reactive.kotlin.handler

import com.reactive.kotlin.model.Order
import com.reactive.kotlin.repository.OrderRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class OrderHandler (private val orderRepository: OrderRepository){
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    fun createOrder(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Order::class.java)
            .flatMap { order ->
                val currentTime =  LocalDateTime.now().format(formatter)
                order.lastModifiedTS = currentTime
                order.orderDate = currentTime
                val savedCustomer = orderRepository.save(order)
                ServerResponse.ok().body(savedCustomer, Order::class.java)
            }.switchIfEmpty(ServerResponse.badRequest().build())
    }

}