package com.jardim.betes.utils.extensions

fun String.isEmailValid() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
