package com.jardim.betes.utils

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jardim.betes.utils.constants.LoginConstants

fun hasBlankInputs(listInputViews : List<TextInputEditText>): Boolean {
    var blankInputs = false

    listInputViews.forEach {
        if (it.text.isNullOrBlank()){
            (it.parent.parent as TextInputLayout).error = LoginConstants.BLANK_INPUT
            blankInputs = true
        } else {
            (it.parent.parent as TextInputLayout).error = null
        }
    }

    return blankInputs
}