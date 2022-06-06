package com.dumihi.todoapp.data.service

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.BuyItem
import com.dumihi.todoapp.data.model.Call

interface BuyService {
    fun getBuys(): TodoFlow<List<BuyItem>>
}