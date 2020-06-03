package com.jardim.betes.data

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.jardim.betes.db.dao.UserDao
import com.jardim.betes.db.entities.User
import com.jardim.betes.domain.model.result.FirebaseAuthResult
import com.jardim.betes.domain.model.user.CreateUserData
import com.jardim.betes.domain.model.user.SignInUserData
import com.jardim.betes.domain.repository.UserRepository
import com.jardim.betes.common.constants.LoginConstants.GOOGLE_CREDENTIAL_NULL
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.withContext

import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl(private val firebaseAuth : FirebaseAuth,
                         private val userDao : UserDao,
                         private val scope : CoroutineContext) : UserRepository {

    override suspend fun createUser(userData: CreateUserData) = CompletableDeferred<FirebaseAuthResult>().apply {
        withContext(scope){
            firebaseAuth.createUserWithEmailAndPassword(userData.email, userData.password)
                .addOnSuccessListener {
                    this@apply.complete(FirebaseAuthResult.instanceSuccess)
                }
                .addOnFailureListener {
                    this@apply.complete(FirebaseAuthResult.getErrorInstance(it.message))
                }
        }
    }.await()

    override suspend fun createUserWithGoogle(account: GoogleSignInAccount?) = CompletableDeferred<FirebaseAuthResult>().apply {
        withContext(scope){
            account?.let {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnSuccessListener {
                        this@apply.complete(FirebaseAuthResult.instanceSuccess)
                    }
                    .addOnFailureListener {
                        this@apply.complete(FirebaseAuthResult.getErrorInstance(it.message))
                    }
            } ?: this@apply.complete(FirebaseAuthResult.getErrorInstance(GOOGLE_CREDENTIAL_NULL))
        }
    }.await()

    override suspend fun signIn(userData: SignInUserData) = CompletableDeferred<FirebaseAuthResult>().apply {
        withContext(scope) {
            firebaseAuth.signInWithEmailAndPassword(userData.email, userData.password)
                .addOnSuccessListener {
                    this@apply.complete(FirebaseAuthResult.instanceSuccess)
                }
                .addOnFailureListener {
                    this@apply.complete(FirebaseAuthResult.getErrorInstance(it.message))
                }
        }
    }.await()

    override suspend fun saveUserLocal(user: User) {
        withContext(scope) {
            userDao.saveUser(user)
        }
    }

    override suspend fun updateNickName(nickname: String) = CompletableDeferred<FirebaseAuthResult>().apply {
        withContext(scope) {
            firebaseAuth.currentUser?.let {
                it.updateProfile(UserProfileChangeRequest.Builder()
                    .setDisplayName(nickname)
                    .build())
                    .addOnSuccessListener {
                        this@apply.complete(FirebaseAuthResult.instanceSuccess)
                    }
                    .addOnFailureListener { error ->
                        this@apply.complete(FirebaseAuthResult.getErrorInstance(error.message))
                    }
            }
        }
    }.await()

    override suspend fun signout(): FirebaseAuthResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun resetPassword(): FirebaseAuthResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updatePassword(): FirebaseAuthResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateEmail(): FirebaseAuthResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteUser(): FirebaseAuthResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}