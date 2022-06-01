package com.technical.task.data.service.common

import com.technical.task.data.service.UserDTO
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GoRestService {

    @GET(USERS)
    fun getUsers() : Call<List<UserDTO>>

    @POST(USERS)
    fun addUser(userDTO: UserDTO) : Call<Any>

    @DELETE("$USERS/{$USER_ID}")
    fun deleteUser(@Path(USER_ID) userId: Int) : Call<Any>
}

private const val USERS = "users"
private const val USER_ID = "userId"