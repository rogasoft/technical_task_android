package com.technical.task.common

import androidx.core.util.PatternsCompat

fun String.validateEmailPattern() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.validateMinimumLength() = this.length >= MINIMUM_LENGTH

private const val MINIMUM_LENGTH = 5