package com.dumihi.todoapp.data.model

import com.google.gson.annotations.SerializedName

data class Call(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("number") val number: String
)