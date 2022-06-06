package com.dumihi.todoapp.data.repository

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.Call

interface CallRepository  {
    fun getCalls(): TodoFlow<List<Call>>
}