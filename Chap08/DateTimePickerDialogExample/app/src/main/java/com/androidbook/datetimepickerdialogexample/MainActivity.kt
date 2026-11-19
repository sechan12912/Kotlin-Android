package com.androidbook.datetimepickerdialogexample

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val listener = DatePickerDialog.OnDateSetListener{ datePicker, year,
                            month, dayofMonth ->
                Toast.makeText(this,  "선택날짜 : "+year+ "년 " +(month + 1)+"월 "+
                            dayofMonth+"일", Toast.LENGTH_LONG).show()
            }
            val pickerDialog = DatePickerDialog(this, listener, year, month, day)
            pickerDialog.setTitle("DatePickerDialog")
            pickerDialog.show()
        }

        val button2 : Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val listener = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                Toast.makeText(this,  "선택시간 : "+hourOfDay+ "시 " +minute +"분", Toast.LENGTH_LONG).show()
            }
            val pickerDialog = TimePickerDialog(this, listener, hour, minute, true)
            pickerDialog.setTitle("TimePickerDialog")
            pickerDialog.show()
        }

    }
}