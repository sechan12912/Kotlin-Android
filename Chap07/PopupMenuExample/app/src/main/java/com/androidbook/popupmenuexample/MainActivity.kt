package com.androidbook.popupmenuexample

import android.graphics.Color
import android.os.Bundle
import android.view.MenuInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnObj: Button
    lateinit var layoutObj: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        layoutObj = findViewById(R.id.main)
        btnObj = findViewById(R.id.button)

        btnObj.setOnClickListener {
            val popupMenu : PopupMenu = PopupMenu(applicationContext,btnObj)

            val inflater: MenuInflater = popupMenu.menuInflater
            inflater.inflate(R.menu.popup_menu,  popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                Toast.makeText(applicationContext, " 메뉴항목 : " + item.title,
                                Toast.LENGTH_SHORT).show()
                when(item.itemId) {
                    R.id.itemColorR -> layoutObj.setBackgroundColor(Color.RED)
                    R.id.itemColorG -> layoutObj.setBackgroundColor(Color.GREEN)
                    R.id.itemColorB -> layoutObj.setBackgroundColor(Color.BLUE)
                }
                true
            })
        }

    }
}
