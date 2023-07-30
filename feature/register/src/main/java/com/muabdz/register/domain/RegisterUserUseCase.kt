package com.muabdz.register.domain

class RegisterUserUseCase {

    // TODO: implement later
    data class RegisterParam(
        val birthdate: String,
        val email: String,
        val gender: String,
        val password: String,
        val username: String
    )
}