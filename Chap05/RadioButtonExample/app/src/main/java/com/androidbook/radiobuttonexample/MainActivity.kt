package com.androidbook.radiobuttonexample

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var rbObj1: RadioButton
    lateinit var rbObj2: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rbObj1=findViewById(R.id.radioButton1)
        rbObj2=findViewById(R.id.radioButton2)
        var listener  = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                when(buttonView.id){
                    R.id.radioButton1 ->   Toast.makeText(applicationContext,
                                            "강아지를 선택했습니다.", Toast.LENGTH_LONG).show()
                    R.id.radioButton2->   Toast.makeText(applicationContext,
                                            "고양이를 선택했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
        rbObj1.setOnCheckedChangeListener(listener)
        rbObj2.setOnCheckedChangeListener(listener)
    }
}