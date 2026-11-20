package com.androidbook.recyclerviewexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        var   itemList = ArrayList<MyItem>()
        itemList.add(MyItem("Monday",  R.drawable.image1))
        itemList.add(MyItem("Tuesday",  R.drawable.image2))
        itemList.add(MyItem("Wednesday",  R.drawable.image3))
        itemList.add(MyItem("Thursday",  R.drawable.image4))
        itemList.add(MyItem("Friday",   R.drawable.image5))
        itemList.add(MyItem("Saturday",  R.drawable.image6))
        itemList.add(MyItem("Sunday",  R.drawable.image7))

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = MyAdapter(itemList)
        recyclerView.adapter = adapter
    }
}