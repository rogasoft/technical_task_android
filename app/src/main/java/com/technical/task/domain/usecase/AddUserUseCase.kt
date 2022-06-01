package com.technical.task.domain.usecase

import com.technical.task.domain.repository.AddUserRepository
import com.technical.task.presentation.list.model.UserModel

class AddUserUseCase(private val addUserRepository: AddUserRepository) {

    fun addUser(userModel: UserModel) = addUserRepository.addUser(userModel)
}