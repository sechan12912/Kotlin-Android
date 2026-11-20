package com.androidbook.listviewexample

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList
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
        val listview: ListView = findViewById(R.id.listview )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        val listview: ListView = findViewById(R.id.listview)
        listview.adapter = adapter
        listview.setOnItemClickListener { adapterView, view, i, l ->
            val itemValue: String = adapterView?.getItemAtPosition(i).toString()
            Toast.makeText(applicationContext, itemValue, Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }

    }
}
