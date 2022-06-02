package com.technical.task.data.service.common

import com.technical.task.BuildConfig

fun generateAuthorizationHeader() = "$BEARER ${BuildConfig.ACCESS_TOKEN}"

private const val BEARER = "Bearer"