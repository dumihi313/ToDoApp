package com.dumihi.todoapp.data.service

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.Call

interface CallService {
    fun getCalls(): TodoFlow<List<Call>>
}