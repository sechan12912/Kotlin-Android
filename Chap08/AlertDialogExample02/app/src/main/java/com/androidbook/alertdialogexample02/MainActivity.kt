package com.androidbook.alertdialogexample02

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
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

        val button : Button = findViewById(R.id.button)

        button.setOnClickListener {

            val listItems = arrayOf("Android App", "Kotlin", "Java")
            val alertDialog = AlertDialog.Builder(this)

           alertDialog.setIcon(R.drawable.kitty)

            alertDialog.setTitle("AlertDialog")

            val itemListener   = DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, listItems[which], Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            val btnListener   = DialogInterface.OnClickListener { dialog, i ->
                dialog.cancel()

            }

            alertDialog.setItems(listItems, itemListener)

            alertDialog.setPositiveButton("예",btnListener)

            alertDialog.show()

        }
        val button2 : Button = findViewById(R.id.button2)

        button2.setOnClickListener {

            val listItems = arrayOf("Android App", "Kotlin", "Java")
            val alertDialog = AlertDialog.Builder(this)

            alertDialog.setIcon(R.drawable.kitty)

            alertDialog.setTitle("AlertDialog")

            val itemListener   = DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, listItems[which], Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            val btnListener   = DialogInterface.OnClickListener { dialog, i ->
                dialog.cancel()

            }

            alertDialog.setSingleChoiceItems(listItems, -1, itemListener)

            alertDialog.setPositiveButton("예",btnListener)

            alertDialog.show()

        }

        val button3 : Button = findViewById(R.id.button3)

        button3.setOnClickListener {

            val listItems = arrayOf("Android App", "Kotlin", "Java")
            val checkedArray = booleanArrayOf(true, false, false )
            val alertDialog = AlertDialog.Builder(this)

            alertDialog.setIcon(R.drawable.kitty)

            alertDialog.setTitle("AlertDialog")

            val itemListener   = DialogInterface.OnMultiChoiceClickListener { dialog, which , b ->
                Toast.makeText(this, listItems[which], Toast.LENGTH_LONG).show()
                checkedArray[which] = b
                dialog.dismiss()
            }

            val btnListener   = DialogInterface.OnClickListener { dialog, i ->
                dialog.cancel()

            }

            alertDialog.setMultiChoiceItems(listItems, checkedArray, itemListener)

            alertDialog.setPositiveButton("예",btnListener)

            alertDialog.show()

        }

    }
}