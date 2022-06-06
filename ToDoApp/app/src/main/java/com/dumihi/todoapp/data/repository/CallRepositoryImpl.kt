package com.dumihi.todoapp.data.repository

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.Call
import com.dumihi.todoapp.data.repository.CallRepository
import com.dumihi.todoapp.data.service.CallService
import javax.inject.Inject

class CallRepositoryImpl @Inject constructor(
    private val service: CallService
) : CallRepository {

    override fun getCalls(): TodoFlow<List<Call>> {
        //        return flow {
//            listOf<Call>()
//        }
        return service.getCalls()
    }
}