package com.dumihi.todoapp.data.repository

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.Call
import com.dumihi.todoapp.data.model.TodoResult
import com.dumihi.todoapp.data.service.BaseRetrofitService
import com.dumihi.todoapp.data.service.CallService
import kotlinx.coroutines.flow.flow

class FakeCallsRepositoryImpl: BaseRetrofitService(), CallService {

    override fun getCalls(): TodoFlow<List<Call>> {
        return flow {
            emit(TodoResult.Success(listOf()))
        }
    }
}