package com.jardim.betes.ui.login.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.jardim.betes.R

import com.jardim.betes.ui.login.LoginNavigate
import com.jardim.betes.ui.login.LoginViewModel
import com.jardim.betes.utils.constants.LoginConstants.BLANK_INPUT
import com.jardim.betes.utils.constants.LoginConstants.EMAIL_VIEW
import com.jardim.betes.utils.constants.LoginConstants.NAME_VIEW
import com.jardim.betes.utils.constants.LoginConstants.PASSWORD_VIEW
import kotlinx.android.synthetic.main.fragment_create_user.*
import org.koin.android.ext.android.inject


class CreateUserFragment(private val navigate: LoginNavigate) : Fragment(), LifecycleOwner {
    private val loginViewModel : LoginViewModel by inject()

    private val listInputViews by lazy {
        listOf(
            createuser_input_email,
            createuser_input_first_name,
            createuser_input_password
        )
    }

    override fun onStart() {
        super.onStart()

        initViews()
    }

    override fun onResume() {
        super.onResume()

        initObservables()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return this.view ?: inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    private fun initViews() {
        createuser_image_back.setOnClickListener {
            navigate.goToLogin()
        }

        createuser_button_create.setOnClickListener {
            if (hasBlankInputs().not()){
                //create user here
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

    private fun initObservables() {
        loginViewModel.inputErrorFounded().observe(this, Observer(::errorObserve))
    }

    private fun errorObserve(mapError : Map<Int, String>) {
        mapError.forEach {
            when(it.key){
                EMAIL_VIEW -> {
                    createuser_input_layout_email.error = it.value
                }

                PASSWORD_VIEW -> {
                    createuser_input_layout_password.error = it.value
                }

                NAME_VIEW -> {
                    createuser_input_layout_first_name.error = it.value
                }

                else -> {
                    Toast.makeText(activity, it.value, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(navigate: LoginNavigate) = CreateUserFragment(navigate)
    }
}
