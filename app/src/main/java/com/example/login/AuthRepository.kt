package com.example.login

object AuthRepository {

    private val users = mutableListOf(
        User("admin", "admin123"),
        User("user1", "pass1"),
        User("user2", "qwerty"),
    )

    sealed class LoginResult {
        object Success : LoginResult()
        object UserNotFound : LoginResult()
        object WrongPassword : LoginResult()
    }

    sealed class RegisterResult {
        object Success : RegisterResult()
        object AlreadyExists : RegisterResult()
    }

    sealed class ForgotPasswordResult {
        object Success : ForgotPasswordResult()
        object UserNotFound : ForgotPasswordResult()
    }

    fun login(login: String, password: String): LoginResult {
        val user = users.find { it.login == login }
            ?: return LoginResult.UserNotFound
        return if (user.password == password) LoginResult.Success else LoginResult.WrongPassword
    }

    fun register(login: String, password: String): RegisterResult {
        if (users.any { it.login == login }) return RegisterResult.AlreadyExists
        users.add(User(login, password))
        return RegisterResult.Success
    }

    fun changePassword(login: String, newPassword: String): ForgotPasswordResult {
        val index = users.indexOfFirst { it.login == login }
        if (index == -1) return ForgotPasswordResult.UserNotFound
        users[index] = users[index].copy(password = newPassword)
        return ForgotPasswordResult.Success
    }
}
