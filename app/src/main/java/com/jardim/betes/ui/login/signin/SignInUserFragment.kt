package com.jardim.betes.ui.login.signin


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jardim.betes.R
import com.jardim.betes.ui.login.LoginNavigate
import com.jardim.betes.utils.hasBlankInputs
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInUserFragment(private val navigate: LoginNavigate) : Fragment() {
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

        initViews()
    }

    private fun initViews() {
        signing_button_login_google.setOnClickListener {

        }

        signing_button_login.setOnClickListener {
            if (hasBlankInputs(listInputViews).not()){

            }
        }

        signing_image_back.setOnClickListener {
            navigate.goToLogin()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(navigate: LoginNavigate) = SignInUserFragment(navigate)
    }
}
