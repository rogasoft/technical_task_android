package com.technical.task.data.service.mapper

import com.technical.task.data.service.AddUserDTO
import com.technical.task.presentation.list.model.UserModel

class AddUserMapper {

    fun mapUserToDTO(userModel: UserModel): AddUserDTO = with(userModel) {
        AddUserDTO(name = name, email = email, gender = gender, status = status)
    }
}