package com.technical.task.data.model

sealed class AddUserState {

    object AddUserSuccess : AddUserState()

    object AddUserFailure : AddUserState()
}