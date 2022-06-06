package com.dumihi.todoapp.data.repository

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.BuyItem
import com.dumihi.todoapp.data.model.Call

interface BuyRepository  {
    fun getBuys(): TodoFlow<List<BuyItem>>
}