package com.jardim.betes.utils.extensions

import com.jardim.betes.domain.model.result.FirebaseAuthResult
import com.jardim.betes.utils.constants.LoginConstants.DEFAULT_ERROR_MESSAGE

fun FirebaseAuthResult.toPair() = Pair(this.operationSuccess, this.operationErrorMessage ?: DEFAULT_ERROR_MESSAGE)