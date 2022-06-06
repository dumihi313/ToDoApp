package com.dumihi.todoapp.data.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.dumihi.todoapp.data.di.DatabaseNameInfo
import com.dumihi.todoapp.data.di.DatabaseVersionInfo
import javax.inject.Inject

class SellDbHelper @Inject constructor(
    context: Context,
    @DatabaseNameInfo dbName: String,
    @DatabaseVersionInfo dbVersion: Int
) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        insertDefaultSellItems(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun clearDatabase() {
        writableDatabase.execSQL("DELETE FROM ItemToSell")
        writableDatabase.close()
    }

    fun insertDefaultSellItems(db: SQLiteDatabase) {
        // Gets the data repository in write mode
        val typeSale = 2
        // Create a new map of values, where column names are the keys

        val values1 = ContentValues().apply {
            put(SellReaderContract.SellEntry.COLUMN_NAME_PRODUCT_NAME, "iPhone X")
            put(SellReaderContract.SellEntry.COLUMN_NAME_PRICE, 150000)
            put(SellReaderContract.SellEntry.COLUMN_NAME_QUANTITY, 1)
            put(SellReaderContract.SellEntry.COLUMN_NAME_TYPE, typeSale)
        }

        val values2 = ContentValues().apply {
            put(SellReaderContract.SellEntry.COLUMN_NAME_PRODUCT_NAME, "TV")
            put(SellReaderContract.SellEntry.COLUMN_NAME_PRICE, 38000)
            put(SellReaderContract.SellEntry.COLUMN_NAME_QUANTITY, 2)
            put(SellReaderContract.SellEntry.COLUMN_NAME_TYPE, typeSale)
        }

        val values3 = ContentValues().apply {
            put(SellReaderContract.SellEntry.COLUMN_NAME_PRODUCT_NAME, "Table")
            put(SellReaderContract.SellEntry.COLUMN_NAME_PRICE, 12000)
            put(SellReaderContract.SellEntry.COLUMN_NAME_QUANTITY, 1)
            put(SellReaderContract.SellEntry.COLUMN_NAME_TYPE, typeSale)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(SellReaderContract.SellEntry.TABLE_NAME, null, values1)
        db.insert(SellReaderContract.SellEntry.TABLE_NAME, null, values2)
        db.insert(SellReaderContract.SellEntry.TABLE_NAME, null, values3)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS ${SellReaderContract.SellEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${SellReaderContract.SellEntry.COLUMN_NAME_PRODUCT_NAME} TEXT," +
                    "${SellReaderContract.SellEntry.COLUMN_NAME_PRICE} INTEGER," +
                    "${SellReaderContract.SellEntry.COLUMN_NAME_QUANTITY} INTEGER," +
                    "${SellReaderContract.SellEntry.COLUMN_NAME_TYPE} INTEGER)"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${SellReaderContract.SellEntry.TABLE_NAME}"
    }
}