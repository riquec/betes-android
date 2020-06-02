package com.jardim.betes.domain.model.result

data class CreateUserResult (
    val userCreated : Boolean,
    val createUserError : String?
)