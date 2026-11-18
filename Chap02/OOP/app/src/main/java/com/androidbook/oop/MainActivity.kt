package com.androidbook.oop

import android.os.Bundle
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

        var myObj = Member()
        myObj.name = "홍길동"
        myObj.age = 20
        myObj.gender = 'M'
        myObj.printMemberInfo()

    }
}
open class Animal4 {
    open fun sound(): String = "동물"

    open fun sound(): String {
        return "동물 소리"
    }

    constructor() {
        Log.d("코틀린 : 객체 지향 프로그래밍", "Animal4 생성자 호출")
    }
}
class Dog4 : Animal4 {
    override val name: String = "강아지"
    override fun sound(): String {
        return "멍멍!"
    }
    fun printNames() {
        Log.d("코틀린 : 객체 지향 프로그래밍", "this.name = ${this.name}")
        Log.d("코틀린 : 객체 지향 프로그래밍", "super.name = ${super.name}")
    }
    fun callSoundMethods() {
        Log.d("코틀린 : 객체 지향 프로그래밍", "this.sound() 호출 결과: ${this.sound()}")
        Log.d("코틀린 : 객체 지향 프로그래밍", "super.sound() 호출 결과: ${super.sound()}")
    }
    constructor() : super() {
        Log.d("코틀린 : 객체 지향 프로그래밍", "super.sound() 호출 결과: ${super.sound()}")
    }
}