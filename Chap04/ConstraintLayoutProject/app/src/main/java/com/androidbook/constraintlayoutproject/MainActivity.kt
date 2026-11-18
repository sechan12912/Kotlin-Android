package com.androidbook.constraintlayoutproject

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var ivObjA: ImageView
    lateinit var ivObjB: ImageView
    lateinit var edtObjResult: EditText
    var diceNumber = intArrayOf(R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6)
    var numA = 0
    var numB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivObjA = findViewById(R.id.imageView1)
        ivObjB = findViewById(R.id.imageView2)
        edtObjResult = findViewById(R.id.editText)

        numA = (Math.random()*6).roundToInt()+1
        numB = (Math.random()*6).roundToInt()+1

        ivObjA.setImageResource(diceNumber[numA-1])
        ivObjB.setImageResource(diceNumber[numB-1])

    }

    fun onClickChoice(view: View?) {
            val num: Int = edtObjResult.text.toString().toInt()
            if (num == numA + numB) Toast.makeText(applicationContext, "맞았습니다", Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext, "틀렸습니다", Toast.LENGTH_SHORT).show()
    }
}
