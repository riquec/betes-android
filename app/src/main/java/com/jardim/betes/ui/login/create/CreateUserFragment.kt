package com.jardim.betes.ui.login.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jardim.betes.R
import com.jardim.betes.ui.login.LoginNavigate
import com.jardim.betes.ui.login.LoginViewModel
import com.jardim.betes.utils.constants.LoginConstants.DEFAULT_ERROR_MESSAGE
import com.jardim.betes.utils.hasBlankInputs
import kotlinx.android.synthetic.main.fragment_create_user.*
import org.koin.android.ext.android.inject


class CreateUserFragment(private val navigate: LoginNavigate) : Fragment(), LifecycleOwner {
    private val loginViewModel : LoginViewModel by inject()

    private val listInputViews by lazy {
        listOf(
            createuser_input_email,
            createuser_input_nick_name,
            createuser_input_password
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return this.view ?: inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onResume() {
        super.onResume()

        initObservables()
        initViews()
    }

    private fun initObservables() {
        loginViewModel.whenAuthEvent().observe(this, Observer(::createUserObserver))
    }

    private fun initViews() {
        createuser_image_back.setOnClickListener {
            navigate.goToLogin()
        }

        createuser_button_login_google.setOnClickListener {
            navigate.goToGoogleAuth()
        }

        createuser_button_login.setOnClickListener {
            if (hasBlankInputs(listInputViews).not()){
                loginViewModel.createUser(
                    createuser_input_nick_name.text.toString(),
                    createuser_input_email.text.toString(),
                    createuser_input_password.text.toString()
                )
            }
        }
    }

    private fun createUserObserver(result : Pair<Boolean, String?>){
        if (result.first){

        }
        else{
            this.view?.let {
                Snackbar.make(it,result.second ?: DEFAULT_ERROR_MESSAGE, Snackbar.LENGTH_LONG).apply {
                    animationMode = Snackbar.ANIMATION_MODE_FADE
                    show()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(navigate: LoginNavigate) = CreateUserFragment(navigate)
    }
}
