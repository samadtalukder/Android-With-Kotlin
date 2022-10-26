package com.samad_talukder.androidsqlitecrudexample.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.FABOURITE_TABLE
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.KEY_DATE
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.KEY_DESCRIPTION
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.KEY_ID
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.KEY_TITLE

class FavouriteDBHelper(context: Context) : SQLiteOpenHelper(
    context,
    DBInfo.DATABASE_NAME, null, DBInfo.DATABASE_VERSION
) {
    // When the database is created for the first time then it will be call.
    override fun onCreate(db: SQLiteDatabase?) {
        val createFavouriteTable = "CREATE TABLE " + FABOURITE_TABLE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TITLE + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_DATE + " TEXT" +
                ")"
        db!!.execSQL(createFavouriteTable)
    }

    // when the database needs to be upgraded this time then it will be call.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Simplest implementation is to drop all old tables and recreate them
        if (oldVersion != newVersion) {
            db!!.execSQL("DROP TABLE IF EXISTS $FABOURITE_TABLE")
            onCreate(db)
        }
    }
}