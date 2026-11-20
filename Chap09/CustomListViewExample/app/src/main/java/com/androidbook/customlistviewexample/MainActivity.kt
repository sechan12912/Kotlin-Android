package com.androidbook.customlistviewexample

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
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
        val itemList = ArrayList<MyItem>()
        itemList.add(MyItem("Monday",  R.drawable.image1))
        itemList.add(MyItem("Tuesday",  R.drawable.image2))
        itemList.add(MyItem("Wednesday",  R.drawable.image3))
        itemList.add(MyItem("Thursday",  R.drawable.image4))
        itemList.add(MyItem("Friday",   R.drawable.image5))
        itemList.add(MyItem("Saturday",  R.drawable.image6))
        itemList.add(MyItem("Sunday",  R.drawable.image7))

        val listview: ListView = findViewById(R.id.listview  )
        val adapter = MyAdapter(itemList)
        listview.adapter = adapter

        listview.setOnItemClickListener { adapterView, view, i, l ->
            val itemValue = adapterView?.getItemAtPosition(i) as MyItem
            Toast.makeText(applicationContext, itemValue.name,
                Toast.LENGTH_SHORT).show()
        }

    }
}