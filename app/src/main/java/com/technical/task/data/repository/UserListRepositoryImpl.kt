package com.technical.task.data.repository

import com.technical.task.data.model.UserListState
import com.technical.task.data.service.UserDTO
import com.technical.task.data.service.common.GoRestService
import com.technical.task.data.service.common.NetworkController
import com.technical.task.data.service.common.ServiceAction
import com.technical.task.data.service.mapper.UserListMapper
import com.technical.task.domain.repository.UserListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

class UserListRepositoryImpl(
    private val goRestService: GoRestService,
    private val networkController: NetworkController,
    private val userListMapper: UserListMapper
) : UserListRepository {

    override fun downloadUserList(): Flow<UserListState> {
        val async = GlobalScope.async(Dispatchers.IO) {
            val call = goRestService.getUsers()
            when(val response = networkController.performNetworkRequest(call)) {
                is ServiceAction.Success -> validateList(response.data)
                is ServiceAction.GeneralError,
                ServiceAction.TimeoutError,
                ServiceAction.NetworkError -> flowOf(UserListState.UserListDownloadFailure)
            }
        }
        return runBlocking { async.await() }
    }

    private fun validateList(list: List<UserDTO>?): Flow<UserListState> =
        if (list.isNullOrEmpty()) flowOf(UserListState.UserListIsEmpty)
        else flowOf(UserListState.UserListDownloadSuccess(userListMapper.mapUserList(list)))
}