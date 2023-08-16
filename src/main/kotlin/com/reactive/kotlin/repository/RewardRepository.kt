package com.reactive.kotlin.repository

import com.reactive.kotlin.model.Reward
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface RewardRepository:ReactiveCrudRepository<Reward,String> {
}