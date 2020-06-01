package com.jardim.betes.ui.login.validate


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jardim.betes.R
import com.jardim.betes.ui.login.LoginNavigate

class ValidateUserFragment(private val navigate: LoginNavigate) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_validate_user, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance(navigate: LoginNavigate) = ValidateUserFragment(navigate)
    }
}
