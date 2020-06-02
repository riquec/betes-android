package com.jardim.betes.ui.login.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jardim.betes.domain.model.user.CreateUserData
import com.jardim.betes.domain.repository.UserRepository
import kotlinx.coroutines.launch

class CreateUserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val createUserEventLiveData = MutableLiveData<Pair<Boolean, String?>>()

    fun whenCreateUserEventHappen() : LiveData<Pair<Boolean, String?>> = createUserEventLiveData

    fun createUser(nickName : String,
                   email : String,
                   password : String){
        viewModelScope.launch {
            val createResult = userRepository.createUser(CreateUserData(email, password))
            createUserEventLiveData.postValue(Pair(createResult.operationSuccess, createResult.operationErrorMessage))
        }
    }
}