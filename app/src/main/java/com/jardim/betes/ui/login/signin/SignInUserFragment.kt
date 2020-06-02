package com.jardim.betes.ui.login.signin


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

import com.jardim.betes.R
import com.jardim.betes.ui.login.LoginNavigate
import com.jardim.betes.ui.login.LoginViewModel
import com.jardim.betes.utils.constants.LoginConstants
import com.jardim.betes.utils.disable
import com.jardim.betes.utils.enable
import com.jardim.betes.utils.hasBlankInputs
import kotlinx.android.synthetic.main.fragment_create_user.*
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.android.ext.android.inject

class SignInUserFragment(private val navigate: LoginNavigate) : Fragment() {
    private val loginViewModel : LoginViewModel by inject()

    private val listInputViews by lazy {
        listOf(
            signing_input_email,
            signing_input_password
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onResume() {
        super.onResume()

        initObservables()
        initViews()
    }

    private fun initViews() {
        signing_button_login_google.setOnClickListener {
            initLoading()
            navigate.goToGoogleAuth()
        }

        signing_button_login.setOnClickListener {
            initLoading()
            if (hasBlankInputs(listInputViews).not()){
                loginViewModel.doSignIn(signing_input_email.text.toString(), signing_input_password.text.toString())
            }
        }

        signing_image_back.setOnClickListener {
            navigate.goToLogin()
        }
    }

    private fun initObservables() {
        loginViewModel.whenAuthEvent().observe(this, Observer(::singInObserver))
    }

    private fun singInObserver(result : Pair<Boolean, String?>){
        finishLoading()
        if (result.first){

        }
        else{
            this.view?.let {
                Snackbar.make(it,result.second ?: LoginConstants.DEFAULT_ERROR_MESSAGE, Snackbar.LENGTH_LONG).apply {
                    animationMode = Snackbar.ANIMATION_MODE_FADE
                    show()
                }
            }
        }
    }

    private fun initLoading() {
        signing_progress.visibility = View.VISIBLE
        signing_button_login_google.disable()
        signing_button_login.disable()
    }

    private fun finishLoading(){
        signing_progress.visibility = View.INVISIBLE
        signing_button_login_google.enable()
        signing_button_login.enable()
    }


    companion object {
        @JvmStatic
        fun newInstance(navigate: LoginNavigate) = SignInUserFragment(navigate)
    }
}
