package com.jardim.betes.ui.onboarding.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jardim.betes.R
import com.jardim.betes.ui.onboarding.OnboardingNavigate

class IntroductionFragment(private val navigate: OnboardingNavigate) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(navigate: OnboardingNavigate) = IntroductionFragment(navigate)
    }
}
