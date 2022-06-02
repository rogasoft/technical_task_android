package com.technical.task.common

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.technical.task.R

fun View.showGeneralFailureSnackbar() {
    showSnackbar(R.string.general_failure_message)
}

fun View.showNetworkFailureSnackbar() {
    showSnackbar(R.string.network_failure_message)
}

fun View.showDeleteSuccessSnackbar() {
    showSnackbar(R.string.delete_success_message)
}

fun View.showEmptyListSnackbar() {
    showSnackbar(R.string.empty_list_message)
}

private fun View.showSnackbar(message: Int) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}