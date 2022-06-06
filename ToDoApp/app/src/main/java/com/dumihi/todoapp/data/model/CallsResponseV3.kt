package com.dumihi.todoapp.data.model

import com.dumihi.todoapp.data.model.base.ResponseV3
import com.google.gson.annotations.SerializedName

//calls : {[
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
//},
//error : {},
//message: {}
class CallsResponseV3: ResponseV3<List<Call>>() {
    @SerializedName("calls")
    val calls: List<Call> = listOf()

    override fun getData(): List<Call> {
        return calls
    }
}