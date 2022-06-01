package com.technical.task.data.service.mapper

import com.technical.task.data.service.UserDTO
import com.technical.task.presentation.list.model.UserModel

class AddUserMapper {

    fun mapUserToDTO(userModel: UserModel): UserDTO = with(userModel) {
        UserDTO(name = name, email = email)
    }
}