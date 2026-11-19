package com.androidbook.customdialogexample

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button : Button = findViewById(R.id.button)

        button.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val inflater: LayoutInflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_layout, null)
            alertDialog.setView(dialogView)
            val dialog = alertDialog.show()
            val close : Button = dialogView.findViewById(R.id.close)
            close.setOnClickListener {
                val name : EditText = dialogView.findViewById(R.id.name)
                dialog.dismiss()
                Toast.makeText(this, "당신의 이름은 "+name.text+"입니다" , Toast.LENGTH_LONG).show()
            }
        }
    }
}
