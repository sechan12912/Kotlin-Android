package com.androidbook.collection

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lists = mutableListOf("월요일", "화요일", "수요일", "목요일", "금요일")
        val sets = mutableSetOf(6, 10)
        val maps = mutableMapOf(1 to "Java", 2 to "Kotlin", 3 to "Android")

        Log.d("코틀린 : 컬렉션", "초기 리스트: $lists")
        Log.d("코틀린 : 컬렉션", "초기 셋: $sets")
        Log.d("코틀린 : 컬렉션", "초기 맵: $maps")

        lists[0] = "일요일"
        lists.add("토요일")

        sets.add(2)
        sets.add(5)

        maps[1] = "C#"
        maps[4] = "C++"

        Log.d("코틀린 : 컬렉션", "변경 후 ------------------------------")
        Log.d("코틀린 : 컬렉션", "변경된 리스트: $lists")
        Log.d("코틀린 : 컬렉션", "변경된 셋: $sets")
        Log.d("코틀린 : 컬렉션", "변경된 맵: $maps")
    }
}