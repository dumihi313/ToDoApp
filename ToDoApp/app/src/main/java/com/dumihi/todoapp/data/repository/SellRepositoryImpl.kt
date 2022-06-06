package com.dumihi.todoapp.data.repository

import android.content.Context
import android.provider.BaseColumns
import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.database.SellDbHelper
import com.dumihi.todoapp.data.database.SellDbManager
import com.dumihi.todoapp.data.database.SellReaderContract.SellEntry.COLUMN_NAME_PRICE
import com.dumihi.todoapp.data.database.SellReaderContract.SellEntry.COLUMN_NAME_PRODUCT_NAME
import com.dumihi.todoapp.data.database.SellReaderContract.SellEntry.COLUMN_NAME_QUANTITY
import com.dumihi.todoapp.data.database.SellReaderContract.SellEntry.COLUMN_NAME_TYPE
import com.dumihi.todoapp.data.database.SellReaderContract.SellEntry.TABLE_NAME
import com.dumihi.todoapp.data.model.SellItem
import com.dumihi.todoapp.data.model.TodoResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SellRepositoryImpl @Inject constructor(
    val dbManager: SellDbManager,
) : SellRepository {

    override fun getSells(): TodoFlow<List<SellItem>> {
        return dbManager.getAllSellItems()
    }
}