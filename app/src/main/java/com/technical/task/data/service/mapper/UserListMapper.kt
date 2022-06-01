package com.technical.task.data.service.mapper

import com.technical.task.data.service.UserDTO
import com.technical.task.presentation.list.model.UserModel
import java.util.*

class UserListMapper {

    fun mapUserList(list: List<UserDTO>): List<UserModel> {
        return LinkedList<UserModel>().apply {
            list.forEach {
                add(
                    UserModel(
                        name = it.name,
                        email = it.email
                    )
                )
            }
        }
    }
}