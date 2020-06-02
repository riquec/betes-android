package com.jardim.betes.ui.login.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.jardim.betes.R
import com.jardim.betes.ui.login.LoginNavigate
import com.jardim.betes.ui.login.LoginViewModel
import com.jardim.betes.utils.constants.LoginConstants.BLANK_INPUT
import com.jardim.betes.utils.constants.LoginConstants.DEFAULT_MESSAGE
import kotlinx.android.synthetic.main.fragment_create_user.*
import org.koin.android.ext.android.inject


class CreateUserFragment(private val navigate: LoginNavigate) : Fragment(), LifecycleOwner {
    private val viewModel : CreateUserViewModel by inject()
    private val loginViewModel : LoginViewModel by inject()

    private val listInputViews by lazy {
        listOf(
            validate_input_email,
            createuser_input_nick_name,
            validate_input_password
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
        viewModel.whenCreateUserEventHappen().observe(this, Observer(::createUserObserver))
        loginViewModel.authGoogleEvent().observe(this, Observer(::createUserObserver))
    }

    private fun initViews() {
        createuser_image_back.setOnClickListener {
            navigate.goToLogin()
        }

        validate_button_login_google.setOnClickListener {
            navigate.goToGoogleAuth()
        }

        validate_button_login.setOnClickListener {
            if (hasBlankInputs().not()){
                viewModel.createUser(
                    createuser_input_nick_name.text.toString(),
                    validate_input_email.text.toString(),
                    validate_input_password.text.toString()
                )
            }
        }
    }

    private fun hasBlankInputs(): Boolean {
        var blankInputs = false

        listInputViews.forEach {
            if (it.text.isNullOrBlank()){
                (it.parent.parent as TextInputLayout).error = BLANK_INPUT
                blankInputs = true
            } else {
                (it.parent.parent as TextInputLayout).error = null
            }
        }

        return blankInputs
    }

    private fun createUserObserver(result : Pair<Boolean, String?>){
        if (result.first){
            //todo navegar para o menu
        }
        else{
            this.view?.let {
                Snackbar.make(it,result.second ?: DEFAULT_MESSAGE, Snackbar.LENGTH_LONG).apply {
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
