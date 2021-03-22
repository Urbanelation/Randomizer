package com.example.randomizer

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*
import java.text.SimpleDateFormat

// source (mostly based on):
// https://blog.frsarker.com/kotlin/save-data-into-sqlite-database-using-kotlin-in-android.html

class DBHelper (context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(
            "CREATE TABLE $TABLE_NAME " +
                    "($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_SLOT TEXT, $COLUMN_DATA TEXT, " +
                    "$COLUMN_CREATION_DATE TEXT, $COLUMN_LAST_MODIFIED_DATE TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertRow(slotName: String, data: String){
        val values = ContentValues()
        values.put(COLUMN_SLOT, slotName)
        values.put(COLUMN_DATA, data)
        values.put(COLUMN_LAST_MODIFIED_DATE, currentDate)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateRow(rowId: String, slotName: String, data: String){
        val values = ContentValues()
        values.put(COLUMN_SLOT, slotName)
        values.put(COLUMN_DATA, data)
        values.put(COLUMN_LAST_MODIFIED_DATE, currentDate)

        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(rowId))
        db.close()
    }

    fun deleteRow(rowId: String){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(rowId))
        db.close()
    }

    fun getAllRows(): Cursor?{
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        const val DATABASE_VERSION=1
        const val DATABASE_NAME="app_data_memory.db"
        const val TABLE_NAME="app_memory"

        const val COLUMN_ID="id"
        const val COLUMN_SLOT="slot_name"
        const val COLUMN_DATA="data"
        const val COLUMN_LAST_MODIFIED_DATE="last_modified_date"
        const val COLUMN_CREATION_DATE="creation_date"

        private val fmt = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate: String = fmt.format(Date())
    }

}