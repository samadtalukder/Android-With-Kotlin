package com.samad_talukder.androidsqlitecrudexample.db.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_DATE
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_DESCRIPTION
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_ID
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_TITLE

data class FavouriteEntity(
    val title: String,
    val description: String,
    val date: String,
) {
    @SuppressLint("Range")
    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
        cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
    )

    fun getContentValues(): ContentValues {
        val values = ContentValues()
        values.put(COLUMN_TITLE, this.title)
        values.put(COLUMN_DESCRIPTION, this.description)
        values.put(COLUMN_DATE, this.date)

        return values
    }
}
