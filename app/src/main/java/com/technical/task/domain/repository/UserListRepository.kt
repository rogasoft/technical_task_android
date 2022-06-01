package com.technical.task.domain.repository

import com.technical.task.data.model.UserListState
import kotlinx.coroutines.flow.Flow

interface UserListRepository {

    fun downloadUserList(): Flow<UserListState>
}