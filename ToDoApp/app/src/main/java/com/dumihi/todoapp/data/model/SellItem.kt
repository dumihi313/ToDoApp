package com.dumihi.todoapp.data.model

import com.dumihi.todoapp.data.local.Product
import com.dumihi.todoapp.data.local.ProductType
import com.google.gson.annotations.SerializedName

data class SellItem(
    @SerializedName("id") override val id: Int,
    @SerializedName("name")  override var name: String,
    @SerializedName("price")  override val price: Int,
    @SerializedName("quantity") override val quantity: Int,
    @SerializedName("type") override val productType: Int = getType(ProductType.Sell),
) : Product()