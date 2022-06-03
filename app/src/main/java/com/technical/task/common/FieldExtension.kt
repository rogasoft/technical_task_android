package com.technical.task.common

import android.util.Patterns

fun String.validateEmailPattern() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.validateMinimumLength() = this.length >= MINIMUM_LENGTH

private const val MINIMUM_LENGTH = 5