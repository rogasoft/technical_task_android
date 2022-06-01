package com.technical.task.data.repository

import com.technical.task.data.model.AddUserState
import com.technical.task.data.service.common.GoRestService
import com.technical.task.data.service.common.NetworkController
import com.technical.task.data.service.common.ServiceAction
import com.technical.task.data.service.mapper.AddUserMapper
import com.technical.task.domain.repository.AddUserRepository
import com.technical.task.presentation.list.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

class AddUserRepositoryImpl(
    private val goRestService: GoRestService,
    private val networkController: NetworkController,
    private val addUserMapper: AddUserMapper
) : AddUserRepository {

    override fun addUser(userModel: UserModel): Flow<AddUserState> {
        val async = GlobalScope.async(Dispatchers.IO) {
            val call = goRestService.addUser(addUserMapper.mapUserToDTO(userModel))
            when(networkController.performNetworkRequest(call)) {
                is ServiceAction.Success -> flowOf(AddUserState.AddUserSuccess)
                is ServiceAction.GeneralError,
                ServiceAction.TimeoutError,
                ServiceAction.NetworkError -> flowOf(AddUserState.AddUserFailure)
            }
        }
        return runBlocking { async.await() }
    }
}