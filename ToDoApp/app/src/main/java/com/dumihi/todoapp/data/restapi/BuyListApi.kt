package com.dumihi.todoapp.data.restapi

import com.dumihi.todoapp.data.model.BuyItem
import retrofit2.http.GET

interface BuyListApi {
    @GET("buy/")
    suspend fun getBuyList():  retrofit2.Response<List<BuyItem>>
}