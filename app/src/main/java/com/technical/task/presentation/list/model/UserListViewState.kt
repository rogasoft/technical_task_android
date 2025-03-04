package com.technical.task.presentation.list.model

sealed class UserListViewState {

    object EmptyState : UserListViewState()

    object LoadingState : UserListViewState()

    class LoadListSuccess(val list: List<UserModel>) : UserListViewState()

    object DeleteSuccess : UserListViewState()

    object NetworkFailure : UserListViewState()

    object GeneralFailure : UserListViewState()
}