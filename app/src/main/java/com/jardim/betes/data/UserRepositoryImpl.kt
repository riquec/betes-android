package com.jardim.betes.data

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jardim.betes.domain.model.result.CreateUserResult
import com.jardim.betes.domain.model.user.CreateUserData
import com.jardim.betes.domain.repository.UserRepository
import com.jardim.betes.utils.constants.LoginConstants.DEFAULT_MESSAGE
import com.jardim.betes.utils.constants.LoginConstants.GOOGLE_CREDENTIAL_NULL
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.withContext

import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl(private val firebaseAuth : FirebaseAuth,
                         private val scope : CoroutineContext) : UserRepository {
    override suspend fun createUser(userData: CreateUserData) = CompletableDeferred<CreateUserResult>().apply {
        withContext(scope){
            firebaseAuth.createUserWithEmailAndPassword(userData.email, userData.password)
                .addOnSuccessListener {
                    this@apply.complete(CreateUserResult(true, null))
                }
                .addOnFailureListener {
                    this@apply.complete(CreateUserResult(false, it.message))
                }
        }
    }.await()

    override suspend fun createUserWithGoogle(account: GoogleSignInAccount?) = CompletableDeferred<CreateUserResult>().apply {
        withContext(scope){
            account?.let {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnSuccessListener {
                        this@apply.complete(CreateUserResult(true, null))
                    }
                    .addOnFailureListener {
                        CreateUserResult(false, it.message)
                    }
            } ?: this@apply.complete(CreateUserResult(false, GOOGLE_CREDENTIAL_NULL))
        }
    }.await()
}