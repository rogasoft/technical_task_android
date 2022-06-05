package com.technical.task.presentation.dialog.model

sealed class UserDialogViewState {

    object EmptyState : UserDialogViewState()

    object LoadingState : UserDialogViewState()

    object NameValidationFailure : UserDialogViewState()

    object EmailValidationFailure : UserDialogViewState()

    object AddSuccess : UserDialogViewState()

    object AddFailure : UserDialogViewState()

    object NetworkFailure : UserDialogViewState()
}