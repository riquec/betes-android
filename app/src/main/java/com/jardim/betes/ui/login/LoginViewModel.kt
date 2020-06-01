package com.jardim.betes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jardim.betes.utils.constants.FragmentsKey.LOGIN_FRAGMENT_KEY

class LoginViewModel : ViewModel() {
    var currentFragment = LOGIN_FRAGMENT_KEY

    private val inputErrorLiveData = MutableLiveData<Map<Int,String>>()

    fun inputErrorFounded() : LiveData<Map<Int,String>> = inputErrorLiveData

    fun validateEmail(email : String?) {

    }
}