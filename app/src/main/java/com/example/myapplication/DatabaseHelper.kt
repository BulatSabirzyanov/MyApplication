package com.example.myapplication


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DatabaseClass.DATABASE_NAME, null, DatabaseClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseClass.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DatabaseClass.SQL_DELETE_TABLE)
        onCreate(db)
    }

}