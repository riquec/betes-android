package com.jardim.betes.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.jardim.betes.db.entities.User
import com.jardim.betes.domain.model.result.FirebaseAuthResult
import com.jardim.betes.domain.model.user.CreateUserData
import com.jardim.betes.domain.model.user.SignInUserData

interface UserRepository {
    suspend fun createUser(userData : CreateUserData) : FirebaseAuthResult

    suspend fun createUserWithGoogle(account: GoogleSignInAccount?): FirebaseAuthResult

    suspend fun signIn(userData: SignInUserData) : FirebaseAuthResult

    suspend fun signout() : FirebaseAuthResult

    suspend fun resetPassword() : FirebaseAuthResult

    suspend fun updatePassword() : FirebaseAuthResult

    suspend fun updateNickName(nickname: String) : FirebaseAuthResult

    suspend fun updateEmail() : FirebaseAuthResult

    suspend fun deleteUser() : FirebaseAuthResult

    suspend fun saveUserLocal(user : User)
}