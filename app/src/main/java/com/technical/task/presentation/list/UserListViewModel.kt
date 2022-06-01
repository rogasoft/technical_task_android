package com.technical.task.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.task.data.model.UserListState
import com.technical.task.domain.usecase.GetUserListUseCase
import com.technical.task.presentation.list.model.UserListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val baseStateFlow = MutableStateFlow<UserListViewState>(UserListViewState.LoadingState)
    val stateFlow = baseStateFlow.asStateFlow()

    fun getUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserListUseCase.getUserList().collect {
                when(it) {
                    is UserListState.UserListDownloadSuccess -> updateState(UserListViewState.LoadListSuccess(it.list))
                    UserListState.UserListIsEmpty -> updateState(UserListViewState.EmptyState)
                    UserListState.UserListDownloadFailure -> updateState(UserListViewState.LoadListFailure)
                }
            }
        }
    }

    private fun updateState(viewState: UserListViewState) {
        baseStateFlow.value = viewState
    }
}