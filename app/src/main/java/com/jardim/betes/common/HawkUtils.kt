package com.jardim.betes.common

import com.jardim.betes.common.constants.HawkConstants.ONBOARDING_VIEW_KEY
import com.orhanobut.hawk.Hawk


fun isOnboardingViewed() = Hawk.get(ONBOARDING_VIEW_KEY, false)

fun setOnboardingViewd() = Hawk.put(ONBOARDING_VIEW_KEY, true)