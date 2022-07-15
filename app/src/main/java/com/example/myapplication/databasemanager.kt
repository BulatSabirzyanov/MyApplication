package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class Databasemanager(context: Context) {
    val databaseHelper= DatabaseHelper(context)
    var db: SQLiteDatabase? = null

    fun createUser(username: String, name: String, surname: String, email: String, password: String){
        db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseClass.COLUMN_NAME_USERNAME, username)
            put(DatabaseClass.COLUMN_NAME_NAME, name)
            put(DatabaseClass.COLUMN_NAME_SURNAME, surname)
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
                val name = cursor?.getColumnName(cursor.getColumnIndex(DatabaseClass.COLUMN_NAME_NAME)).toString()
                val surname = cursor?.getColumnName(cursor.getColumnIndex(DatabaseClass.COLUMN_NAME_SURNAME)).toString()
                val email =
                    cursor?.getString(cursor.getColumnIndex(DatabaseClass.COLUMN_NAME_EMAIL)).toString()
                val password =
                    cursor?.getString(cursor.getColumnIndex(DatabaseClass.COLUMN_NAME_PASSWORD))
                        .toString()
                data.add(username)
                data.add(name)
                data.add(surname)
                data.add(email)
                data.add(password)
            }
        } catch (e: Exception){
            data.add("")
            data.add("")
            data.add("")
            data.add("")
            data.add("")
        }
        return data
    }

    fun closeDb(){
        databaseHelper.close()
    }
}