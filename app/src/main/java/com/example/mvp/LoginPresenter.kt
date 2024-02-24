package com.example.mvp

interface LoginPresenter {

    fun doLogin(email: String, password: String)

    interface LoginView {
        fun invalidEmail()
        fun invalidPassword()
        fun onSuccess(response: String)
        fun onFailure(message: String)
    }
}