package com.dumihi.todoapp.data.local

import com.dumihi.todoapp.data.model.User

interface UserStorage {
    fun load(): User

    fun save(user: User)

    fun hasAgreedStreamingPolicy(): Boolean

    fun markAgreedStreamingPolicy()
}