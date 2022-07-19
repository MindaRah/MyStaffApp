package com.example.mypeopleandroomapp.utils

import android.util.Log

fun debugLogger(errorMessage: String?) {
    Log.d("TAG_BUG", errorMessage.toString())
}