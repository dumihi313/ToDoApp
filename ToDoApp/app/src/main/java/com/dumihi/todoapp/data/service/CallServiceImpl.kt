package com.dumihi.todoapp.data.service

import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.restapi.CallListApi
import com.dumihi.todoapp.data.model.Call
import javax.inject.Inject

class CallServiceImpl @Inject constructor(
    private val callListApi: CallListApi
) : BaseRetrofitService(), CallService {

    override fun getCalls(): TodoFlow<List<Call>> {
        return execute {
            callListApi.getCallList()
        }
    }
}