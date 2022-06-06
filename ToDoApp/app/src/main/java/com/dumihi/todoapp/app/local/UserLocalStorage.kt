package com.dumihi.todoapp.app.local

import android.content.SharedPreferences
import com.dumihi.todoapp.data.local.UserStorage
import com.dumihi.todoapp.data.model.Credential
import com.dumihi.todoapp.data.model.User
import com.dumihi.todoapp.data.di.UserPrefs
import javax.inject.Inject

private const val KEY_USER_ACCESS_TOKEN = "pref_access_token"

private const val KEY_HAS_AGREED_STREAMING_POLICY = "pref_has_agreed_streaming_policy"

class UserLocalStorage @Inject constructor(
    @UserPrefs private val pref: SharedPreferences
) : UserStorage {
    override fun load(): User {
        val credential = Credential(pref.getString(KEY_USER_ACCESS_TOKEN, "") ?: "")

        return User(credential)
    }

    override fun save(user: User) {
        pref.edit().apply {
            putString(KEY_USER_ACCESS_TOKEN, user.credential.accessToken)
        }.apply()
    }

    override fun hasAgreedStreamingPolicy(): Boolean =
        pref.getBoolean(KEY_HAS_AGREED_STREAMING_POLICY, false)

    override fun markAgreedStreamingPolicy() {
        pref.edit().putBoolean(KEY_HAS_AGREED_STREAMING_POLICY, true).apply()
    }
}