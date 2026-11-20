package com.cookandroid.sqlitedatabaseexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(context: Context): SQLiteOpenHelper(context, "Users3.db", null, 1) {

     override fun onCreate(db: SQLiteDatabase?) {
                val query = "CREATE TABLE UsersTable (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), age INTEGER)"

                db?.execSQL(query)
     }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

                val query = "DROP TABLE IF EXISTS UsersTable"
                db?.execSQL(query)
                onCreate(db)

    }

}