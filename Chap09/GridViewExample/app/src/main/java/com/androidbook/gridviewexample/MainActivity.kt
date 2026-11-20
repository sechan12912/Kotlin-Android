package com.androidbook.gridviewexample

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
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

        val array = arrayOf("Monday","Tuesday","Wednesday","Thursday",
                            "Friday","Saturday","Sunday" )
        val gridview: GridView = findViewById(R.id.gridview)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        gridview.adapter = adapter
        gridview.setOnItemClickListener { adapterView, view, i, l ->
            val itemValue: String = adapterView?.getItemAtPosition(i).toString()
            Toast.makeText(applicationContext, itemValue, Toast.LENGTH_SHORT).show()
        }

    }
}