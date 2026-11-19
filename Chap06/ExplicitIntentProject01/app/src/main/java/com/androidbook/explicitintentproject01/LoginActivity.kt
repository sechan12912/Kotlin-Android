package com.androidbook.explicitintentproject01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lgoin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnObj : Button = findViewById(R.id.button)
        btnObj.setOnClickListener {
           val etObj01 : EditText = findViewById(R.id.editText01)
            val etObj02 : EditText = findViewById(R.id.editText02)
            Toast.makeText(applicationContext, "아이디 : "+ etObj01.text.toString()+"\n비밀번호 : "+ etObj02.text.toString(), Toast.LENGTH_LONG).show()
        }

    }
}