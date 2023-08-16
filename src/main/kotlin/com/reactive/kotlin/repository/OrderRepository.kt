package com.reactive.kotlin.repository

import com.reactive.kotlin.model.Order
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface OrderRepository: ReactiveCrudRepository<Order,String> {
}