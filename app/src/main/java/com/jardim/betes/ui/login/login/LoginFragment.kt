package com.jardim.betes.ui.login.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jardim.betes.R
import com.jardim.betes.ui.login.LoginNavigate
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment(private val navigate: LoginNavigate) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onResume() {
        super.onResume()

        initViews()
    }

    private fun initViews() {
        login_button_getstarted.setOnClickListener {
            navigate.goToCreateUser()
        }

        login_button_singin.setOnClickListener {
            navigate.goToValidatUser()
        }

    }

    companion object {
        fun newInstance(navigate: LoginNavigate) =
            LoginFragment(navigate)
    }
}
