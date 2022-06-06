package com.dumihi.todoapp.data.database

import android.content.ContentValues
import android.provider.BaseColumns
import com.dumihi.todoapp.app.di.AppScoped
import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.di.DataScoped
import com.dumihi.todoapp.data.model.SellItem
import com.dumihi.todoapp.data.model.TodoResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@DataScoped
class SellDbManager @Inject constructor(val dbHelper: SellDbHelper) {

    fun getAllSellItems(): TodoFlow<List<SellItem>> {
        return flow<TodoResult<List<SellItem>>> {
            val db = dbHelper.readableDatabase

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            val projection = arrayOf(
                BaseColumns._ID,
                SellReaderContract.SellEntry.COLUMN_NAME_PRODUCT_NAME,
                SellReaderContract.SellEntry.COLUMN_NAME_PRICE,
                SellReaderContract.SellEntry.COLUMN_NAME_QUANTITY,
                SellReaderContract.SellEntry.COLUMN_NAME_TYPE
            )
            try {
                val cursor = db.query(
                    SellReaderContract.SellEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
                )

                val sellItems = mutableListOf<SellItem>()
                with(cursor) {
                    while (moveToNext()) {
                        val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                        val itemName =
                            getString(getColumnIndexOrThrow(SellReaderContract.SellEntry.COLUMN_NAME_PRODUCT_NAME))
                        val itemPrice = getInt(getColumnIndexOrThrow(SellReaderContract.SellEntry.COLUMN_NAME_PRICE))
                        val itemQuantity = getInt(getColumnIndexOrThrow(SellReaderContract.SellEntry.COLUMN_NAME_QUANTITY))
                        val itemType =
                            getInt(getColumnIndexOrThrow(SellReaderContract.SellEntry.COLUMN_NAME_TYPE))
                        sellItems.add(
                            SellItem(
                                itemId.toInt(),
                                itemName,
                                itemPrice,
                                itemQuantity,
                                itemType
                            )
                        )
                    }
                }
                cursor.close()
                emit(TodoResult.Success(sellItems))
            } catch (e: Exception) {
                emit(TodoResult.Error(e.cause ?: Throwable(e.message)))
            }
        }
    }

   fun clearDatabase() {
       dbHelper.clearDatabase()
    }

}