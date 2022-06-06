package com.dumihi.todoapp.app.utils

import com.dumihi.todoapp.data.model.TodoResult
import kotlinx.coroutines.flow.Flow

typealias TodoFlow<T> = Flow<TodoResult<T>>