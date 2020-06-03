package com.jardim.betes.common.extensions

import com.jardim.betes.domain.model.result.FirebaseAuthResult
import com.jardim.betes.common.constants.LoginConstants.DEFAULT_ERROR_MESSAGE

fun FirebaseAuthResult.toPair() = Pair(this.operationSuccess, this.operationErrorMessage ?: DEFAULT_ERROR_MESSAGE)