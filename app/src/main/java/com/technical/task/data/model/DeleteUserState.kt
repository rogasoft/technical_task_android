package com.technical.task.data.model

sealed class DeleteUserState {

    object DeleteUserSuccess : DeleteUserState()

    object DeleteUserFailure : DeleteUserState()
}