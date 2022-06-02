package com.technical.task.presentation.dialog.model

sealed class UserDialogViewState {

    object EmptyState : UserDialogViewState()

    object LoadingState : UserDialogViewState()

    object AddSuccess : UserDialogViewState()

    object AddFailure : UserDialogViewState()
}