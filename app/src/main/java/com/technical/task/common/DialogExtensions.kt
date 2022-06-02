package com.technical.task.common

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.technical.task.R

fun Context.showAlertDialog(action: () -> Unit) {
    MaterialAlertDialogBuilder(this)
        .setTitle(resources.getString(R.string.dialog_delete_title))
        .setPositiveButton(R.string.dialog_delete_positive_button) { dialog, which ->
            action()
        }
        .setNegativeButton(R.string.dialog_delete_negative_button) { dialog, which ->
            dialog.dismiss()
        }
        .show()
}