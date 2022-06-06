package com.dumihi.todoapp.data.local

abstract class Product {
    abstract val id: Int
    abstract var name: String
    abstract val price: Int
    abstract val quantity: Int
    abstract val productType: Int

    companion object {
//        fun getType(type: Int): ProductType {
//            return when (type) {
//                1 -> ProductType.Buy
//                else -> ProductType.Sell
//            }
//        }

        fun getType(productType: ProductType): Int {
            return productType.type
        }
    }
}

enum class ProductType(val type: Int) {
    Buy(1),
    Sell(2)
}