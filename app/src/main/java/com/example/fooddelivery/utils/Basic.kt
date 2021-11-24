package com.example.fooddelivery.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> T.scope(block: T.() -> Unit) {
    block(this)
}

fun myLog(message: String, tag: String = "TTT") {
    Log.d(tag, message)
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message,duration)
}
