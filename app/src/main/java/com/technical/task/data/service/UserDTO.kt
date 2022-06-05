package com.technical.task.data.service

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName(ID) var id: Int,
    @SerializedName(NAME) var name: String,
    @SerializedName(EMAIL) var email: String,
    @SerializedName(GENDER) var gender: String,
    @SerializedName(STATUS) var status: String
)

private const val ID = "id"
private const val NAME = "name"
private const val EMAIL = "email"
private const val GENDER = "gender"
private const val STATUS = "status"