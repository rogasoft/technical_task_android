package com.technical.task.data.model

import com.technical.task.presentation.list.model.UserModel

sealed class UserListState {

    class UserListDownloadSuccess(val list: List<UserModel>) : UserListState()

    object UserListIsEmpty : UserListState()

    object UserListDownloadFailure: UserListState()
}