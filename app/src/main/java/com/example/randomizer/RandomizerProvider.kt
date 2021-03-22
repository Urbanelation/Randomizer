package com.example.randomizer

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri


private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI("com.randomizer.provider", "data", 1)
}

class RandomizerProvider: ContentProvider() {

    private var db: SQLiteDatabase? = null
    private var dbHelper: DBHelper? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor : Cursor? = null;
        when(sUriMatcher.match(uri)) {
            1 -> { // data URI
                cursor = dbHelper?.getAllRows();
            }
        }
        return cursor;
    }

    override fun onCreate(): Boolean {
        dbHelper = context?.let { DBHelper(it, null) }
        /*if (dbHelper != null) {
            db = dbHelper.writableDatabase
        }
        return db != null*/
        return dbHelper != null;
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }
}