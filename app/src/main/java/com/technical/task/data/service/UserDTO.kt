package com.technical.task.data.service

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName(NAME) var name: String,
    @SerializedName(EMAIL) var email: String
)

private const val NAME = "name"
private const val EMAIL = "email"