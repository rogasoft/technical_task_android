package com.technical.task.presentation.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.task.data.model.AddUserState
import com.technical.task.domain.usecase.AddUserUseCase
import com.technical.task.presentation.dialog.model.UserDialogViewState
import com.technical.task.presentation.list.model.UserListViewState
import com.technical.task.presentation.list.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDialogViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val baseStateFlow = MutableStateFlow<UserDialogViewState>(UserDialogViewState.EmptyState)
    val stateFlow = baseStateFlow.asStateFlow()

    fun addUser(name: String, email: String) {
        updateState(UserDialogViewState.LoadingState)
        val userModel = UserModel(id = 0, name = name, email = email, gender = "male", status = "active")
        viewModelScope.launch(Dispatchers.IO) {
            addUserUseCase.addUser(userModel).collect {
                when (it) {
                    AddUserState.AddUserSuccess -> updateState(UserDialogViewState.AddSuccess)
                    AddUserState.AddUserFailure -> updateState(UserDialogViewState.AddFailure)
                }
            }
        }
    }

    private fun updateState(viewState: UserDialogViewState) {
        baseStateFlow.value = viewState
    }
}