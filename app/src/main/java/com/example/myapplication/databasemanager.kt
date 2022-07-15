package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    val DatabaseHelper= DatabaseHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = DatabaseHelper.writableDatabase
    }

    fun addUser(username: String, email: String, password: String){
        val values = ContentValues().apply {
            put(DatabaseClass.COLUMN_NAME_USERNAME, username)
            put(DatabaseClass.COLUMN_NAME_EMAIL, email)
            put(DatabaseClass.COLUMN_NAME_PASSWORD, password)
        }
        db?.insert(DatabaseClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getUserData(email: String): ArrayList<String> {
        val data = ArrayList<String>()
        try {
            val query =
                "select * from ${DatabaseClass.TABLE_NAME} where ${DatabaseClass.COLUMN_NAME_EMAIL} = '$email'"
            val cursor = db?.rawQuery(query, null)
            with(cursor) {
                this?.moveToFirst()
                val username =
                    cursor?.getString(cursor.getColumnIndex(DatabaseClass.COLUMN_NAME_USERNAME))
                        .toString()
                val email =
                    cursor?.getString(cursor.getColumnIndex(DatabaseClass.COLUMN_NAME_EMAIL)).toString()
                val password =
                    cursor?.getString(cursor.getColumnIndex(DatabaseClass.COLUMN_NAME_PASSWORD))
                        .toString()
                data.add(username)
                data.add(email)
                data.add(password)
            }
        } catch (e: Exception){
            data.add("")
            data.add("")
            data.add("")
        }
        return data
    }

    fun correctPassword(email: String, password: String): Boolean{
        val data = getUserData(email)
        return password == data[2]
    }

    fun closeDb(){
        DatabaseHelper.close()
    }
}