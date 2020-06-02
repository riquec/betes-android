package com.jardim.betes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.jardim.betes.domain.repository.UserRepository
import com.jardim.betes.utils.constants.FragmentsKey.LOGIN_FRAGMENT_KEY
import com.jardim.betes.utils.constants.LoginConstants.DEFAULT_MESSAGE
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var currentFragment = LOGIN_FRAGMENT_KEY

    private val authGoogleLiveData = MutableLiveData<Pair<Boolean, String>>()

    fun authGoogleEvent() : LiveData<Pair<Boolean, String>> = authGoogleLiveData

    fun doAuthWithGoogle(task: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            try {
                val account = task.getResult(ApiException::class.java)
                val result = userRepository.createUserWithGoogle(account)
                authGoogleLiveData.postValue(Pair(result.userCreated, result.createUserError ?: DEFAULT_MESSAGE))
            } catch (e : ApiException){
                authGoogleLiveData.postValue(Pair(false, e.message ?: DEFAULT_MESSAGE))
            }
        }
    }
}