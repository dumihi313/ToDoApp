package com.dumihi.todoapp.data.model

import com.dumihi.todoapp.data.local.UserStorage
import com.dumihi.todoapp.data.model.User.Companion.GUEST
import com.dumihi.todoapp.utils.SingletonHolder


class MetamonUserManager private constructor(
    private val storage: UserStorage
) {
    companion object : SingletonHolder<UserStorage, MetamonUserManager>(::MetamonUserManager)

    private var _currentUser: User = storage.load()

    var currentUser: User
        get() = _currentUser
        set(value) {
            _currentUser = value
            storage.save(_currentUser)
        }

    fun isUserLoggedIn(): Boolean = currentUser.credential.accessToken.isNotEmpty()

    fun clear() {
        currentUser = GUEST
    }
}