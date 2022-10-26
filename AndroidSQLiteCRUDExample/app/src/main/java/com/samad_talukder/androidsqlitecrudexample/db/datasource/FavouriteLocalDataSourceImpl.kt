package com.samad_talukder.androidsqlitecrudexample.db.datasource

import android.database.sqlite.SQLiteOpenHelper
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo
import com.samad_talukder.androidsqlitecrudexample.db.model.FavouriteEntity

class FavouriteLocalDataSourceImpl(private val sqLiteOpenHelper: SQLiteOpenHelper) :
    FavouriteLocalDataSource {

    override fun getFavouriteList(): List<FavouriteEntity> {
        val db = sqLiteOpenHelper.readableDatabase
        val sql = "SELECT * FROM ${DBInfo.FABOURITE_TABLE}"
        val favouriteEntity = ArrayList<FavouriteEntity>()

        val cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {
            do {
                favouriteEntity.add(FavouriteEntity(cursor))
            } while (cursor.moveToNext())
        }

        if (!cursor.isClosed) {
            cursor.close()
        }

        return favouriteEntity
    }

    override fun getFavouriteItem(id: Int): FavouriteEntity? {
        val db = sqLiteOpenHelper.readableDatabase
        val sql = "SELECT * FROM ${DBInfo.FABOURITE_TABLE} WHERE ${DBInfo.COLUMN_ID} = $id"
        var favouriteEntity: FavouriteEntity? = null

        val cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {
            favouriteEntity = FavouriteEntity(cursor)
        }

        if (!cursor.isClosed) {
            cursor.close()
        }

        return favouriteEntity

    }

    override fun saveFavouriteItem(favouriteEntityList: List<FavouriteEntity>) {
        val db = sqLiteOpenHelper.writableDatabase
        // It's a good idea to wrap our insert in a transaction.
        // This helps with performance and ensures consistency of the database.
        db.beginTransaction()

        for (favEntity in favouriteEntityList) {
            db.insert(DBInfo.FABOURITE_TABLE, null, favEntity.getContentValues())
        }

        db.setTransactionSuccessful()
        db.endTransaction()
    }

    override fun saveFavouriteItem(favouriteEntityList: FavouriteEntity): Boolean {
        val db = sqLiteOpenHelper.writableDatabase
        // It's a good idea to wrap our insert in a transaction.
        // This helps with performance and ensures consistency of the database.
        db.beginTransaction()

        val check: Long =
            db.insert(DBInfo.FABOURITE_TABLE, null, favouriteEntityList.getContentValues())
        val num = -1
        if (check == num.toLong()) {
            return false
        }
        db.setTransactionSuccessful()
        db.endTransaction()

        return true
    }

    override fun updateFavouriteItem(favouriteEntityList: FavouriteEntity): Boolean {
        val db = sqLiteOpenHelper.writableDatabase
        // It's a good idea to wrap our insert in a transaction.
        // This helps with performance and ensures consistency of the database.


        val check: Long = db.update(
            DBInfo.FABOURITE_TABLE,
            favouriteEntityList.getContentValues(),
            "id" + "=?",
            arrayOf("4")
        ).toLong()

        val num = -1

        if (check == num.toLong()) {
            return false
        }

        db.close()

        return true
    }

    override fun deleteFavouriteItem(id: Int) {
        val db = sqLiteOpenHelper.writableDatabase
        db.delete(DBInfo.FABOURITE_TABLE, null, null)
    }

    override fun deleteAllFavouriteItem() {
        val db = sqLiteOpenHelper.writableDatabase
        db.delete(DBInfo.FABOURITE_TABLE, null, null)
    }
}