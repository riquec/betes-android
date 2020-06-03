package com.jardim.betes.common

import androidx.appcompat.widget.AppCompatButton

fun AppCompatButton.enable(){
    this.alpha = 0.6f
    this.isEnabled = true
}

fun AppCompatButton.disable(){
    this.alpha = 1f
    this.isEnabled = false
}