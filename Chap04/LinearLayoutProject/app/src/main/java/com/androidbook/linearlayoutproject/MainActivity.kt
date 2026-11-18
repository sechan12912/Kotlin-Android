package com.androidbook.linearlayoutproject

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnObj1: ImageButton
    lateinit var btnObj2: ImageButton
    lateinit var btnObj3: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnObj1 = findViewById(R.id.imageButton1)
        btnObj2 = findViewById(R.id.imageButton2)
        btnObj3 = findViewById(R.id.imageButton3)

    }

    fun onClickChoice(view: View?) {
        when (view?.id) {
            R.id.imageButton1 -> Toast.makeText(applicationContext, "틀렸습니다", Toast.LENGTH_SHORT).show()
            R.id.imageButton2 -> Toast.makeText(applicationContext, "맞았습니다", Toast.LENGTH_SHORT).show()
            R.id.imageButton3 -> Toast.makeText(applicationContext, "틀렸습니다", Toast.LENGTH_SHORT).show()

        }

    }
}
