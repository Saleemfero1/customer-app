package com.reactive.kotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.Date
@Table("rewards")
data class Reward(
    val rewardsKey:String,
    @Id
    val rewardsId:String,
    val orderNo:String,
    val customerId:String,
    val rewardsEarned:Int,
    val rewardsRedeemed:Int,
    val rewardsBalance:Int,
    val rewardsDate:Date
)

/*
{
  "rewardsKey":"",
  "rewardsId": "100006383728",
  "orderNo":"100006383728",
  "customerId": "6383728",
  "rewardsEarned": "150",
  "rewardsRedeemed": "50",
  "rewardsBalance":"100",
  "rewardsDate": "2022-03-20T12:20:00+0000"
}
* */