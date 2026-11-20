package com.cookandroid.sqlitedatabaseexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "Users.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE UsersTable (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(50), age INTEGER)"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS UsersTable"

        db?.execSQL(query)

        onCreate(db)
    }

    fun insertData(name:String, age:Int) : Boolean{
        val db  : SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put("name",name)
        cv.put("age",age)
        val result = db.insert("UsersTable",null,cv)
        db.close()
        return result > 0
    }

    fun insertData2(name:String, age:Int) {
        val db  = this.writableDatabase
        val query = "INSERT INTO UsersTable (NAME, AGE) VALUES ('" + name +"' , " +  age +" )"

        db.execSQL(query)
        db.close()
    }

    fun selectData():String {
        var str="";
        val db : SQLiteDatabase = this.readableDatabase

        val cursor = db.query(
            "UsersTable",
            null,
            null,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            while (cursor.moveToNext()) {
                var id = cursor.getInt(0)
                var name = cursor.getString(1)
                var age = cursor.getInt(2)
                str +="\n"+"id: "+id+", name: "+name+ ", age: "+age
            }
        }
        db.close()
        return str
    }

   fun selectData2():String {
       var str="";
       val db  : SQLiteDatabase = this.readableDatabase
       val query = "SELECT * FROM UsersTable"
       val cursor = db.rawQuery(query, null)

       if (cursor != null) {
           while (cursor.moveToNext()) {
               var id = cursor.getInt(0)
               var name = cursor.getString(1)
               var age = cursor.getInt(2)
               str +="\n"+"id: "+id+", name: "+name+ ", age: "+age
           }
       }
       db.close()
       return str
   }

    fun updateData() {
            val db : SQLiteDatabase= this.writableDatabase
            val query = "Select * from UsersTable"
            val cursor = db.rawQuery(query, null)

            if(cursor.moveToFirst()){
                do {
                    val cv = ContentValues()
                    cv.put("age", cursor.getString(2) + 1)
                    db.update("UsersTable", cv, "id=? AND name=?",
                            arrayOf(cursor.getString(0), cursor.getString(1)))
                }while (cursor.moveToNext())
            }
        db.close()
    }

    fun updateData2(){
        val db : SQLiteDatabase= this.writableDatabase
        val query = "Select * from UsersTable"
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            do {
                val upAge = cursor.getInt(2) + 1
                val upId = cursor.getString(0)
                val upName = cursor.getString(1)

                val query = "UPDATE UsersTable SET AGE=" + upAge +" WHERE NAME = '" + upName +"' and ID= " + upId

                db.execSQL(query)

            }while (cursor.moveToNext())
        }
        db.close()
    }

    fun deleteData(){
            val db : SQLiteDatabase = this.writableDatabase
            db.delete("UsersTable", null, null)
            db.close()
    }

    fun deleteData2(){
        val db : SQLiteDatabase = this.writableDatabase
        val query = "Delete from UsersTable"
        db.execSQL(query)
        db.close()
    }
}