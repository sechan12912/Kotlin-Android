package com.androidbook.checkboxexample

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var cbObj1: CheckBox
    lateinit var cbObj2: CheckBox
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cbObj1=findViewById(R.id.checkBox1)
        cbObj2=findViewById(R.id.checkBox2)

        var listener  = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                when(buttonView.id){
                    R.id.checkBox1 ->   Toast.makeText(applicationContext, "강아지를 선택했습니다.", Toast.LENGTH_LONG).show()
                    R.id.checkBox2->    Toast.makeText(applicationContext, "고양이를 선택했습니다.", Toast.LENGTH_LONG).show()
                }
            }
            else{
                when(buttonView.id){
                    R.id.checkBox1 ->  Toast.makeText(applicationContext, "강아지를 해제했습니다.", Toast.LENGTH_LONG).show()
                    R.id.checkBox2->   Toast.makeText(applicationContext, "고양이를 해제했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
        cbObj1.setOnCheckedChangeListener(listener)
        cbObj2.setOnCheckedChangeListener(listener)
    }
}