package com.samad_talukder.androidsqlitecrudexample.db

object DBInfo {
    val DATABASE_NAME = "favouriteDB"
    val EXPENSE_DATABASE = "expense_db"
    val DATABASE_VERSION = 1
    val FABOURITE_TABLE = "favourite"
    val EXPENSE_TABLE = "expense"


    val KEY_ID = "id"
    val KEY_TITLE = "title"
    val KEY_DESCRIPTION = "description"
    val KEY_DATE = "date"

    val COLUMN_ID = "id"
    val COLUMN_TITLE = "title"
    val COLUMN_DESCRIPTION = "description"
    val COLUMN_DATE = "date"

    val COLUMN_EXPENSE_NAME = "name"
    val COLUMN_EXPENSE_AMOUNT = "amount"
    val COLUMN_EXPENSE_DESCRIPTION = "description"
    val COLUMN_EXPENSE_CATEGORY = "category"

    fun getCreateTableQuery(): String {
        return "CREATE TABLE $FABOURITE_TABLE (" +
                "$COLUMN_ID INTEGER NOT NULL PRIMARY KEY," +
                "$COLUMN_TITLE TEXT NOT NULL," +
                "$COLUMN_DESCRIPTION TEXT NOT NULL," +
                "$COLUMN_DATE INTEGER NOT NULL," +
                ");"
    }

    val CREATE_EXPENSE_TABLE = ("CREATE TABLE $EXPENSE_TABLE (" +
            "$COLUMN_ID INTEGER NOT NULL PRIMARY KEY," +
            "$COLUMN_EXPENSE_NAME TEXT NOT NULL," +
            "$COLUMN_EXPENSE_AMOUNT INTEGER NOT NULL," +
            "$COLUMN_EXPENSE_CATEGORY INTEGER NOT NULL," +
            "$COLUMN_EXPENSE_DESCRIPTION INTEGER NOT NULL" +
            ")")

    val QUERY_ALL_EXPENSE_LIST = "SELECT * FROM $EXPENSE_TABLE"

    val DROP_EXPENSE_TABLE = "DROP TABLE IF EXISTS $EXPENSE_TABLE"

}