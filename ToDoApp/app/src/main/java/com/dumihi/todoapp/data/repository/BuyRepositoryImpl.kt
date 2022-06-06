package com.dumihi.todoapp.data.repository

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.BuyItem
import com.dumihi.todoapp.data.model.Call
import com.dumihi.todoapp.data.repository.CallRepository
import com.dumihi.todoapp.data.service.BuyService
import com.dumihi.todoapp.data.service.CallService
import javax.inject.Inject

class BuyRepositoryImpl @Inject constructor(
    private val service: BuyService
) : BuyRepository {

    override fun getBuys(): TodoFlow<List<BuyItem>> {
        return service.getBuys()
    }
}