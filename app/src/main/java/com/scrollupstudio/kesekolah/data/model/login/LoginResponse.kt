package com.scrollupstudio.kesekolah.data.model.login

data class LoginResponse(
    val error: Boolean,
    val status: String,
    val user: DataLogin
)