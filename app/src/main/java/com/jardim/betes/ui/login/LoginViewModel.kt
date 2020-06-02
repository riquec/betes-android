package com.jardim.betes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.jardim.betes.domain.model.user.CreateUserData
import com.jardim.betes.domain.model.user.SignInUserData
import com.jardim.betes.domain.repository.UserRepository
import com.jardim.betes.utils.constants.FragmentsKey.LOGIN_FRAGMENT_KEY
import com.jardim.betes.utils.constants.LoginConstants.DEFAULT_ERROR_MESSAGE
import com.jardim.betes.utils.extensions.toPair
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var currentFragment = LOGIN_FRAGMENT_KEY

    private val authFirebaseEvent = MutableLiveData<Pair<Boolean, String>>()

    fun whenAuthEvent() : LiveData<Pair<Boolean, String>> = authFirebaseEvent

    fun doAuthWithGoogle(task: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            try {
                val account = task.getResult(ApiException::class.java)
                val result = userRepository.createUserWithGoogle(account)
                authFirebaseEvent.postValue(result.toPair())
            } catch (e : ApiException){
                authFirebaseEvent.postValue(Pair(false, e.message ?: DEFAULT_ERROR_MESSAGE))
            }
        }
    }

    fun createUserAndUpdateProfile(nickName : String,
                                   email : String,
                                   password : String){
        viewModelScope.launch {
            val createUserResult = userRepository.createUser(CreateUserData(email, password))
            if (createUserResult.operationSuccess){
                val updateUserProfileResult = userRepository.updateNickName(nickName)
                authFirebaseEvent.postValue(updateUserProfileResult.toPair())
            } else {
                authFirebaseEvent.postValue(createUserResult.toPair())
            }
        }
    }

    fun doSignIn(email : String, password: String){
        viewModelScope.launch {
            val result = userRepository.signIn(SignInUserData(email, password))
            authFirebaseEvent.postValue(result.toPair())
        }
    }
}