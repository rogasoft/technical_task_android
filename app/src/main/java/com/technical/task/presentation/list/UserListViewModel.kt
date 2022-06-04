package com.technical.task.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.task.common.NetworkConnectionStateManager
import com.technical.task.data.model.DeleteUserState
import com.technical.task.data.model.UserListState
import com.technical.task.domain.usecase.DeleteUserUseCase
import com.technical.task.domain.usecase.GetUserListUseCase
import com.technical.task.presentation.list.model.UserListViewState
import com.technical.task.presentation.list.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val networkConnectionStateManager: NetworkConnectionStateManager
) : ViewModel() {

    private val baseStateFlow = MutableStateFlow<UserListViewState>(UserListViewState.LoadingState)
    val stateFlow = baseStateFlow.asStateFlow()

    fun getUserList() {
        if (getNetworkState()) {
            updateState(UserListViewState.LoadingState)
            viewModelScope.launch(Dispatchers.IO) {
                getUserListUseCase.getUserList().collect {
                    when (it) {
                        is UserListState.UserListDownloadSuccess ->
                            updateState(UserListViewState.LoadListSuccess(it.list))
                        UserListState.UserListIsEmpty ->
                            updateState(UserListViewState.EmptyState)
                        UserListState.UserListDownloadFailure ->
                            updateState(UserListViewState.GeneralFailure)
                    }
                }
            }
        } else {
            updateState(UserListViewState.NetworkFailure)
        }
    }

    fun deleteUser(userModel: UserModel) {
        if (getNetworkState()) {
            viewModelScope.launch(Dispatchers.IO) {
                deleteUserUseCase.deleteUser(userModel.id).collect {
                    when(it) {
                        DeleteUserState.DeleteUserSuccess ->
                            updateState(UserListViewState.DeleteSuccess)
                        DeleteUserState.DeleteUserFailure ->
                            updateState(UserListViewState.GeneralFailure)
                    }
                }
            }
        } else {
            updateState(UserListViewState.NetworkFailure)
        }
    }

    private fun updateState(viewState: UserListViewState) {
        baseStateFlow.value = viewState
    }

    private fun getNetworkState() = networkConnectionStateManager.isConnected()
}