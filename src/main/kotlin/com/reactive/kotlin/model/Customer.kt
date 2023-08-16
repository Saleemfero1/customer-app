package com.reactive.kotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customers")
data class Customer(
    @Id
    val customerKey:Long?=null,
    var customerId:String,
    var firstName:String,
    var lastName:String,
    var addressLineOne:String,
    var addressLineTwo:String,
    var city:String,
    var state:String,
    var zipcode:String,
    var country:String,
    var phoneNumber:String,
    var emailId:String,
    var status: String?="Active"
)



/*
{
  "customerKey":"",
  "customerId": "6383728",
  "firstName": "Harry",
  "lastName": "Potter",
  "addressLine1": "100 Universal City Plaza",
  "addressLine2": "",
  "city":"Universal City",
  "state":"CA",
  "zipCode":"91608"
  "country":"US",
  "phoneNo":"+1 8912389673",
  "emailId":"harry.potter@gmail.com",
  "status":"Active/Inactive"
}
* */