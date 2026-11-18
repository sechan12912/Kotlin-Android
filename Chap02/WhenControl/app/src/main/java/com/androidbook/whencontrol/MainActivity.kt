package com.androidbook.whencontrol

import android.os.Bundle
import android.util.Log
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

        var day: Int = 2
        var result : String
        when (day) {
            1 -> result = "Monday"
            2 -> result = "Tuesday"
            3 -> result = "Wednesday"
            4 -> result = "Thursday"
            5 -> result = "Friday"
            6 -> result = "Saturday"
            7 -> result = "Sunday"
            else -> result = "Invalid day"
        }
        Log.d("코틀린 : when 조건문 ", result)
    }
}