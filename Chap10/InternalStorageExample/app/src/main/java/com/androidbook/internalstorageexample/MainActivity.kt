package com.androidbook.internalstorageexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

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
        val write : Button = findViewById(R.id.write)

        write.setOnClickListener {
            val edtName : EditText = findViewById(R.id.editTextName)
            val edtAge: EditText = findViewById(R.id.exitTextAge)

            val name: String = edtName.text.toString()+"\n"
            val age: String = edtAge.text.toString()

            val fos : FileOutputStream = openFileOutput("infile.txt",
                                        Context.MODE_PRIVATE)

            fos.write(name.toByteArray())
            fos.write(age.toByteArray())

            fos.close()

            edtName.text.clear()
            edtAge.text.clear()

            Toast.makeText(this, "데이터 저장을 성공했습니다", Toast.LENGTH_SHORT).show()

        }
        val read : Button = findViewById(R.id.read)
        read.setOnClickListener {

            val edtName : EditText = findViewById(R.id.editTextName)
            val edtAge: EditText = findViewById(R.id.exitTextAge)

            val fis: FileInputStream = openFileInput("infile.txt")

            val buf  = fis.bufferedReader().readText().split("\n")

            fis.close()

            edtName.setText(buf[0])
            edtAge.setText(buf[1])

            Toast.makeText(this, "데이터 읽기를 성공했습니다", Toast.LENGTH_SHORT).show()
        }
    }

}