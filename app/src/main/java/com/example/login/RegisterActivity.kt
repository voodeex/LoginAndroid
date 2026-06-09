package com.example.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvGoLogin = findViewById<TextView>(R.id.tvGoLogin)

        btnRegister.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val password = etPassword.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = when (AuthRepository.register(login, password)) {
                AuthRepository.RegisterResult.Success -> {
                    finish()
                    "Регистрация успешна"
                }
                AuthRepository.RegisterResult.AlreadyExists -> "Пользователь уже существует"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        tvGoLogin.setOnClickListener { finish() }
    }
}
