package com.dumihi.todoapp.data.service

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.restapi.BuyListApi
import com.dumihi.todoapp.data.model.BuyItem
import javax.inject.Inject

class BuyServiceImpl @Inject constructor(
    private val buyListApi: BuyListApi
) : BaseRetrofitService(), BuyService {

    override fun getBuys(): TodoFlow<List<BuyItem>> {
        return execute {
            buyListApi.getBuyList()
        }
    }
}