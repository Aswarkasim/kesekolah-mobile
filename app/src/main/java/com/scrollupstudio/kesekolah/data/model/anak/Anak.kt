package com.scrollupstudio.kesekolah.data.model.anak

data class Anak(
    val alamat: String,
    val date_created: String,
    val id_anak: String,
    val id_sekolah: String,
    val id_user: String,
    val is_active: Int,
    val is_hadir: Int,
    val is_jemput: Int,
    val is_ready: Int,
    val latitude: String,
    val longtitude: String,
    val nama_anak: String,
    val nama_sekolah: String,
    val umur: String
)