package com.technical.task.domain.usecase

import com.technical.task.domain.repository.UserListRepository

class GetUserListUseCase(private val userListRepository: UserListRepository) {

    fun getUserList() = userListRepository.downloadUserList()
}