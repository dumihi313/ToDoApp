package com.dumihi.todoapp.data.restapi

import com.dumihi.todoapp.data.model.Call
import retrofit2.http.GET

//[
//{
//    "id": 1,
//    "name": "Jason White",
//    "number": "9993456665"
//},
//{
//    "id": 2,
//    "name": "Wasim Khan",
//    "number": "2345677890"
//},
//{
//    "id": 3,
//    "name": "Amir Khan",
//    "number": "987654320"
//}
//]

interface CallListApi {
    @GET("call/")
    suspend fun getCallList():  retrofit2.Response<List<Call>>
}