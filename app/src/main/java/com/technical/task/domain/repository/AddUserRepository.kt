package com.technical.task.domain.repository

import com.technical.task.data.model.AddUserState
import com.technical.task.presentation.list.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AddUserRepository {

    fun addUser(userModel: UserModel): Flow<AddUserState>
}