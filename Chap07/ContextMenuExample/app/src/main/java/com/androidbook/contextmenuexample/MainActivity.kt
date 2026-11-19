package com.androidbook.contextmenuexample

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
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
        registerForContextMenu(btnObj)
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?,
                                     menuInfo: ContextMenu.ContextMenuInfo?    ) {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)

        return super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext, " 메뉴항목 : " + item.title,
                        Toast.LENGTH_SHORT).show()
        when(item.itemId) {
            R.id.itemColorR -> {
                layoutObj.setBackgroundColor(Color.RED)
                return true
            }
            R.id.itemColorG -> {
                layoutObj.setBackgroundColor(Color.GREEN)
                return true
            }
            R.id.itemColorB -> {
                layoutObj.setBackgroundColor(Color.BLUE)
                return true
            }

        }
        return super.onContextItemSelected(item)
    }
}