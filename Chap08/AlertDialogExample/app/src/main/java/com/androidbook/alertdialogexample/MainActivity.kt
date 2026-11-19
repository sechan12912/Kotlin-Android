package com.androidbook.alertdialogexample

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

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

            alertDialog.setIcon(R.drawable.kitty)
            alertDialog.setTitle("AlertDialog")
            alertDialog.setMessage("앱을 종료하겠습니까?")

            val listener = DialogInterface.OnClickListener { dialog, whitch ->
                when(whitch){
                    DialogInterface.BUTTON_POSITIVE -> {
                        finish()
                        dialog.dismiss()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        Toast.makeText(this, "종료를 취소합니다.", Toast.LENGTH_LONG).show()
                        dialog.cancel()
                    }
                }
            }

            alertDialog.setPositiveButton("예",null)
            alertDialog.setNegativeButton("아니요",null)

            alertDialog.show()
        }
    }
}