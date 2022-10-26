package com.samad_talukder.androidsqlitecrudexample.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_EXPENSE_AMOUNT
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_EXPENSE_CATEGORY
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_EXPENSE_DESCRIPTION
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_EXPENSE_NAME
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.COLUMN_ID
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.CREATE_EXPENSE_TABLE
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.DATABASE_VERSION
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.DROP_EXPENSE_TABLE
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.EXPENSE_DATABASE
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.EXPENSE_TABLE
import com.samad_talukder.androidsqlitecrudexample.db.DBInfo.QUERY_ALL_EXPENSE_LIST
import com.samad_talukder.androidsqlitecrudexample.db.model.ExpenseModel

/**
 * SQLiteOpenHelper used to determine the name and version of the database used
 * @param context
 * @param databaseName
 * @param cursorFactory
 * @param databaseVersion
 *
 */
class ExpenseDBHandler(context: Context?) : SQLiteOpenHelper(
    context,
    EXPENSE_DATABASE,
    null,
    DATABASE_VERSION
) {

    /**
     * onCreate() invoked if the database doesn't exist, meaning the very first time dealing with
     * the SQLite Database
     * @param db
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_EXPENSE_TABLE)
    }

    /**
     * onUpgrade() used to update database schema to the most recent or the existing without losing
     * the data
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db!!.execSQL(DROP_EXPENSE_TABLE)
            onCreate(db)
        }

    }

    fun addExpense(expenseModel: ExpenseModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_EXPENSE_NAME, expenseModel.name)
        contentValues.put(COLUMN_EXPENSE_DESCRIPTION, expenseModel.description)
        contentValues.put(COLUMN_EXPENSE_AMOUNT, expenseModel.amount)
        contentValues.put(COLUMN_EXPENSE_CATEGORY, expenseModel.category)

        val insertSuccess = db.insert(EXPENSE_TABLE, null, contentValues)

        db.close()

        return insertSuccess
    }

    @SuppressLint("Recycle", "Range")
    fun viewExpense(): ArrayList<ExpenseModel> {
        val expList: ArrayList<ExpenseModel> = ArrayList()

        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(QUERY_ALL_EXPENSE_LIST, null)
        } catch (ex: SQLiteException) {
            db.execSQL(QUERY_ALL_EXPENSE_LIST)
            return ArrayList()
        }

        var id: Int
        var name: String
        var description: String
        var amount: Int
        var category: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                name = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_NAME))
                description = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_DESCRIPTION))
                amount = cursor.getInt(cursor.getColumnIndex(COLUMN_EXPENSE_AMOUNT))
                category = cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_CATEGORY))

                val exp = ExpenseModel(id, name, amount, description, category)

                expList.add(exp)

            } while (cursor.moveToNext())
        }

        return expList
    }

    fun updateExpense(expenseModel: ExpenseModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_EXPENSE_NAME, expenseModel.name)
        contentValues.put(COLUMN_EXPENSE_DESCRIPTION, expenseModel.description)
        contentValues.put(COLUMN_EXPENSE_AMOUNT, expenseModel.amount)
        contentValues.put(COLUMN_EXPENSE_CATEGORY, expenseModel.category)

        val updateSuccess =
            db.update(
                EXPENSE_TABLE, contentValues, COLUMN_ID + "=" + expenseModel.id,
                null
            )

        //db.close()

        return updateSuccess

    }

    fun deleteExpense(expenseModel: ExpenseModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, expenseModel.id)

        val deleteSuccess = db.delete(
            EXPENSE_TABLE, COLUMN_ID + "=" + expenseModel.id,
            null
        )

        //db.close()

        return deleteSuccess
    }
}