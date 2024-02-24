package com.example.mvp

import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginPresenterImpl(private var loginView: LoginPresenter.LoginView) : LoginPresenter {

    override fun doLogin(email: String, password: String) {
        if (email.isEmpty()) {
            loginView.invalidEmail()
        } else if (password.isEmpty()) {
            loginView.invalidPassword()
        } else if (email == "test@gmail.com" && password == "1234") {
            CoroutineScope(Dispatchers.IO).launch {
                val retrofitService = RetrofitService.getInstance()
                val response = retrofitService.user()
                if (response.isSuccessful){
                    loginView.onSuccess(response.body.toString())
                }else{
                    loginView.onFailure("unexpected error occurred..please try after sometime")
                }
            }
        }
    }
}