package com.technical.task.domain.repository

import com.technical.task.data.model.DeleteUserState
import kotlinx.coroutines.flow.Flow

interface DeleteUserRepository {

    fun deleteUser(userId: Int): Flow<DeleteUserState>
}