package com.reactive.kotlin.model

import com.reactive.kotlin.model.enums.OrderStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.Date
@Table
data class Order (
    val orderKey:String,
    @Id
    val orderNo:String,
    val customerId:String,
    val rewards: Rewards,
    val orderDate:Date,
    val orderStatus:OrderStatus,
    val totalItems:Int,
    val orderTotal:Int,
    val lastModifiedTS:Date
)

/*
{
  "orderKey": "",
  "orderNo": "100006383728",
  "customerId": "6383728",
  "rewards": {
    "rewardsKey":"",
    "rewardsId": "100006383728",
    "orderNo":"100006383728",
    "customerId": "6383728",
    "rewardsEarned": "150",
    "rewardsRedeemed": "50",
    "rewardsBalance":"100",
    "rewardsDate": "2022-03-20T12:20:00+0000"
  },
  "orderDate": "2022-03-14T22:11:20+0000",
  "orderStatus": "Created/Shipped",
  "totalItems": "3",
  "orderTotal": "100",
  "currency": "USD",
  "lastModifiedTS": "2022-03-20T12:20:00+0000"
}
**/
