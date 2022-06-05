package com.technical.task.data.service.common

import com.technical.task.data.service.AddUserDTO
import com.technical.task.data.service.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface GoRestService {

    @GET(USERS)
    fun getUsers(@Header(AUTHORIZATION) token: String) : Call<List<UserDTO>>

    @POST(USERS)
    fun addUser(@Header(AUTHORIZATION) token: String, @Body addUserDTO: AddUserDTO) : Call<Any>

    @DELETE("$USERS/{$USER_ID}")
    fun deleteUser(@Header(AUTHORIZATION) token: String, @Path(USER_ID) userId: Int) : Call<Any>
}

private const val USERS = "users"
private const val USER_ID = "userId"
private const val PAGE = "page"
private const val AUTHORIZATION = "Authorization"