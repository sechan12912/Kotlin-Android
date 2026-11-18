package com.androidbook.oop

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InheritanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val myDog = Dog2()

        Log.d("코틀린 : 객체 지향 프로그래밍 ", "Dog 클래스: ${myDog.sound()}")
        Log.d("코틀린 : 객체 지향 프로그래밍 ", "Dog 클래스: ${myDog.sleep()}")

    }
}
interface Animal2 {
    fun sound() : String
    fun sleep() : String {
        return "잠을 잔다"
    }
}

class Dog2 : Animal2 {
    override fun sound() : String {
        return "멍멍"
    }
}