package com.androidbook.sharedpreferencesexample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Integer.parseInt

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

        val search :Button  = findViewById(R.id.search)
        search.setOnClickListener {
            val Pref : SharedPreferences = getSharedPreferences("prefile",
                                                Context.MODE_PRIVATE)

            val nameStr = sharedPref.getString("name", null)
            val ageStr = sharedPref.getInt("age",-1)

            if(nameStr!=null || ageStr!=-1 )
                Toast.makeText(applicationContext, "공유설정파일에 저장된 내용은 \n 이름 : "+nameStr + " 나이 : "
                                +ageStr +" 입니다", Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext, "공유설정파일에 저장된 내용이 없습니다",
                Toast.LENGTH_SHORT).show()
        }

        val save : Button = findViewById(R.id.save)
        save.setOnClickListener {
            val username: EditText = findViewById(R.id.txtName)
            val age:EditText = findViewById(R.id.txtAge)

            val nameStr = username.text.toString()
            val ageStr = age.text.toString()

            val sharedPref: SharedPreferences = getSharedPreferences("prefile",
                                                Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putString("name", nameStr)
            editor.putInt("age", parseInt(ageStr))
            editor.apply()
            username.text.clear()
            age.text.clear()
        }

        val delete :Button  = findViewById(R.id.delete)
        delete.setOnClickListener {
            val sharedPref : SharedPreferences = getSharedPreferences("prefile",
                                                Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.remove("name")
            editor.apply()
        }

        val clear :Button  = findViewById(R.id.clear)
        clear.setOnClickListener {
            val sharedPref : SharedPreferences = getSharedPreferences("prefile",
                                                Context.MODE_PRIVATE
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
        }
    }
}