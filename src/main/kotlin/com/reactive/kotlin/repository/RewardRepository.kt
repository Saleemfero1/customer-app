package com.reactive.kotlin.repository

import com.reactive.kotlin.model.Rewards
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface RewardRepository:ReactiveCrudRepository<Rewards,String> {
}