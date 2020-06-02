package com.jardim.betes.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.jardim.betes.domain.model.result.CreateUserResult
import com.jardim.betes.domain.model.user.CreateUserData

interface UserRepository {
    suspend fun createUser(userData : CreateUserData) : CreateUserResult
    suspend fun createUserWithGoogle(account: GoogleSignInAccount?): CreateUserResult
}