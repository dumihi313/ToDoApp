package com.dumihi.todoapp.data.database

import android.provider.BaseColumns

object SellReaderContract {
    // Table contents are grouped together in an anonymous object.
    object SellEntry : BaseColumns {
        const val TABLE_NAME = "ItemToSell"
        const val COLUMN_NAME_PRODUCT_NAME = "name"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_QUANTITY = "quantity"
        const val COLUMN_NAME_TYPE = "type"
    }
}