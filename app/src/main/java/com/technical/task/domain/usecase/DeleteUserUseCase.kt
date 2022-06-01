package com.technical.task.domain.usecase

import com.technical.task.domain.repository.DeleteUserRepository

class DeleteUserUseCase(private val deleteUserRepository: DeleteUserRepository) {

    fun deleteUser(userId: Int) = deleteUserRepository.deleteUser(userId)
}