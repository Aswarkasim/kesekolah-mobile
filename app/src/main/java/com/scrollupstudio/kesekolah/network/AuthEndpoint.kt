package com.scrollupstudio.kesekolah.network

import com.scrollupstudio.kesekolah.data.model.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthEndpoint {

    @FormUrlEncoded
    @POST("auth")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}