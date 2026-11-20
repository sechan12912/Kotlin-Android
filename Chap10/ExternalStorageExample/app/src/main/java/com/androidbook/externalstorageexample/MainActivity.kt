package com.androidbook.externalstorageexample

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
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

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
            val  edtName : EditText = findViewById(R.id.editTextName)
            val edtAge: EditText = findViewById(R.id.exitTextAge)

            val name: String = edtName.text.toString()+"\n"
            val age: String = edtAge.text.toString()
            try {
                val file = File(getExternalFilesDir("MyFileStorage"), "exfile.txt")

                val fos : FileOutputStream = FileOutputStream(file)

                fos.write(name.toByteArray())
                fos.write(age.toByteArray())
                fos.close()

                edtName.text.clear()
                edtAge.text.clear()
                Toast.makeText(this, "데이터 저장을 성공했습니다", Toast.LENGTH_SHORT).show()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        val read : Button = findViewById(R.id.read)
        read.setOnClickListener {
            val  edtName : EditText = findViewById(R.id.editTextName)
            val edtAge: EditText = findViewById(R.id.exitTextAge)

            try {
                val file = File(getExternalFilesDir("MyFileStorage"), "exfile.txt")

                if (file.exists()) {
                  val fis:FileInputStream = FileInputStream(file)

                    val buffer = ByteArray(fis.available())
                    fis.read(buffer)
                    val buf = String(buffer).split("\n")
                    fis.close()

                    edtName.setText(buf[0])
                    edtAge.setText(buf[1])
                    Toast.makeText(this, "데이터 읽기를 성공했습니다", Toast.LENGTH_SHORT).show()

                } else {
                    Log.d("FileRead", "파일이 존재하지 않습니다.")
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}