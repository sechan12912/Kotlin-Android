package com.androidbook.collection

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

        val lists = listOf("월요일", "화요일", "수요일", "목요일", "금요일")
        val sets = setOf(1, 2, 2, 3, 3, 3, "Java", "Java", "Kotlin")
        val maps = mapOf(1 to "Java", 2 to "Kotlin", 3 to "Android")

        Log.d("코틀린 : 컬렉션", "불변 리스트: $lists")
        Log.d("코틀린 : 컬렉션", "첫 번째 요일: ${lists[0]}")

        Log.d("코틀린 : 컬렉션", "불변 셋: $sets")
        Log.d("코틀린 : 컬렉션", "셋에 'Java' 포함 여부: ${"Java" in sets}")

        Log.d("코틀린 : 컬렉션", "불변 맵: $maps")
        Log.d("코틀린 : 컬렉션", "맵에서 key 2에 해당하는 값: ${maps[2]}")

    }
}