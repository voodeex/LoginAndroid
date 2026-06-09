package com.example.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)
        val btnChange = findViewById<Button>(R.id.btnChange)
        val tvGoLogin = findViewById<TextView>(R.id.tvGoLogin)

        btnChange.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val newPassword = etNewPassword.text.toString()

            if (login.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = when (AuthRepository.changePassword(login, newPassword)) {
                AuthRepository.ForgotPasswordResult.Success -> {
                    finish()
                    "Пароль изменён"
                }
                AuthRepository.ForgotPasswordResult.UserNotFound -> "Пользователь не найден"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        tvGoLogin.setOnClickListener { finish() }
    }
}
