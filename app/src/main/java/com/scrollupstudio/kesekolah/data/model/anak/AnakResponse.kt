package com.scrollupstudio.kesekolah.data.model.anak

data class AnakResponse(
    val anak: List<Anak>,
    val error: Boolean,
    val message: String
)