package com.scrollupstudio.kesekolah.data.model.profile

data class ProfileResponse(
    val error: Boolean,
    val status: String,
    val user: List<User>
)