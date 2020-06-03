package com.jardim.betes.domain.model.result

import com.jardim.betes.common.constants.LoginConstants.DEFAULT_ERROR_MESSAGE

data class FirebaseAuthResult (
    val operationSuccess : Boolean,
    val operationErrorMessage : String?
){
    companion object {
        val instanceSuccess = FirebaseAuthResult(true, null)
        fun getErrorInstance(errorMesssage : String?) = FirebaseAuthResult(false,
            errorMesssage ?: DEFAULT_ERROR_MESSAGE)
    }
}