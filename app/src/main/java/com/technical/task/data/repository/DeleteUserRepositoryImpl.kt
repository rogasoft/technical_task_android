package com.technical.task.data.repository

import com.technical.task.data.model.DeleteUserState
import com.technical.task.data.service.common.GoRestService
import com.technical.task.data.service.common.NetworkController
import com.technical.task.data.service.common.ServiceAction
import com.technical.task.domain.repository.DeleteUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

class DeleteUserRepositoryImpl(
    private val goRestService: GoRestService,
    private val networkController: NetworkController
) : DeleteUserRepository {

    override fun deleteUser(userId: Int): Flow<DeleteUserState> {
        val async = GlobalScope.async(Dispatchers.IO) {
            val call = goRestService.deleteUser(userId)
            when(networkController.performNetworkRequest(call)) {
                is ServiceAction.Success -> flowOf(DeleteUserState.DeleteUserSuccess)
                is ServiceAction.GeneralError,
                ServiceAction.TimeoutError,
                ServiceAction.NetworkError -> flowOf(DeleteUserState.DeleteUserFailure)
            }
        }
        return runBlocking { async.await() }
    }
}