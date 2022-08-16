package com.example.train.model

data class Users(

    val id: Long,
    val name: String,
    val photo: String,
    val company: String
)

data class UserDetails(

    val user: Users,
    val details: String
        )

