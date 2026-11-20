package com.androidbook.sqlitedatabaseexample

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cookandroid.sqlitedatabaseexample.MyDatabaseHelper

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

        val insert: Button = findViewById(R.id.btnInsert)
        insert.setOnClickListener {

            val edtName: EditText = findViewById(R.id.editTextName)
            val edtAge: EditText = findViewById(R.id.exitTextAge)

            val name: String = edtName.getText().toString()
            val age: String = edtAge.getText().toString()

            val values = ContentValues().apply {
                put("name", name)
                put("age", age)
            }

            val dbHelper = MyDatabaseHelper(this)
            val db = dbHelper.writableDatabase

            val result = db.insert("UsersTable", null, values)

            if (result != -1L)
                Toast.makeText(applicationContext, "데이터 삽입을 성공했습니다", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(applicationContext, "데이터 삽입을 실패했습니다", Toast.LENGTH_LONG).show()

            db.close()

        }

        val select: Button = findViewById(R.id.btnSelect)
        select.setOnClickListener {
            val tv: TextView = findViewById(R.id.textView)
            var str = "";

            val dbHelper = MyDatabaseHelper(this)
            val db = dbHelper.readableDatabase

            val cursor = db.query("UsersTable", null, null, null, null, null, null)

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    var id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    var name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    var age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
                    str += "\n" + "id: " + id + ", name: " + name + ", age: " + age
                }
            }
            tv.text = str

            cursor.close()
            db.close()
        }

        val update: Button = findViewById(R.id.btnUpdate)
        update.setOnClickListener{
            val dbHelper = MyDatabaseHelper(this)
            val db = dbHelper.writableDatabase

            val query = "SELECT * FROM UsersTable"
            val cursor = db.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val age = cursor.getInt(2)

                    val cv = ContentValues().apply {
                        put("age", age + 1)
                    }
                    db.update(
                        "UsersTable",
                        cv,
                        "id=? AND name=?",
                        arrayOf(id.toString(), name)
                    )
                } while (cursor.moveToNext())
            }

            cursor.close()
            db.close()
        }

        val delete: Button = findViewById(R.id.btnDelete)
        delete.setOnClickListener {
            val dbHelper = MyDatabaseHelper(this)
            val db = dbHelper.writableDatabase

            val result = db.delete("UsersTable", null, null)

            if (result > 0)
                Toast.makeText(this, "모든 데이터를 삭제했습니다.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "삭제할 데이터가 없습니다.", Toast.LENGTH_SHORT).show()

            db.close()
        }
    }
}