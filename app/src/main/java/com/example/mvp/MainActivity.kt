package com.example.mvp

import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.mvp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), LoginPresenter.LoginView {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loginPresenterImpl = LoginPresenterImpl(this)

        binding.loginClick.setOnClickListener {
            if (isNetworkAvailable()) {
                lifecycleScope.launch {
                    launch(Dispatchers.Main) {
                        loginPresenterImpl.doLogin(binding.email.text.toString().trim(), binding.password.text.toString().trim())
                    }
                }
            }else{
                Toast.makeText(this@MainActivity, "no internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun invalidEmail() {
        Toast.makeText(this@MainActivity, "invalid Email", Toast.LENGTH_SHORT).show()
    }

    override fun invalidPassword() {
        Toast.makeText(this@MainActivity, "invalid Password", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(response: String) {
        lifecycleScope.launch {
            binding.response.text = response
        }
    }

    override fun onFailure(message: String) {
        binding.response.text = message
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}