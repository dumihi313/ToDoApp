package com.dumihi.todoapp.data.model

data class User(
    val credential: Credential,
    val loginVia: LoginFrom = LoginFrom.UNKNOWN
) {
    companion object {
        val GUEST = User(Credential(), LoginFrom.UNKNOWN)
    }

    enum class LoginFrom {
        FACEBOOK,
        GOOGLE,
        EMAIL,
        UNKNOWN
    }
}

