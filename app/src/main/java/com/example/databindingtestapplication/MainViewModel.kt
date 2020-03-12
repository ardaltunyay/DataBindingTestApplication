package com.example.databindingtestapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val email = MutableLiveData<String>()

    private val isEmailValid: LiveData<Boolean> = Transformations.map(email) {
        email.value?.let { isEmailValid(it) } ?: false
    }

    val emailInvalidMessage: LiveData<String> = Transformations.map(isEmailValid) {
        when(it) {
            true -> ""
            else -> "Email geçersiz!!!"
        }
    }

    val buttonEnableOrDisable: LiveData<Boolean> = isEmailValid


    val isShowEmail = MutableLiveData<Boolean>()

    init {
        Log.d("MAIN", "MainViewModel init")
    }

    fun showEmail() {
        isShowEmail.value = true
    }

    fun getEmailAddress(): String = email.value?.let { it } ?: ""

    private fun isEmailValid(email: String): Boolean =
        //Should not use android library
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

}