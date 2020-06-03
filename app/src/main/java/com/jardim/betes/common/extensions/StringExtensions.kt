package com.jardim.betes.common.extensions

fun String.isEmailValid() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
