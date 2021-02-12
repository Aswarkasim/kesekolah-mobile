package com.scrollupstudio.kesekolah.network

import com.scrollupstudio.kesekolah.data.model.anak.AnakResponse
import com.scrollupstudio.kesekolah.data.model.profile.ProfileResponse
import com.scrollupstudio.kesekolah.data.model.siap.SiapResponse
import retrofit2.Call
import retrofit2.http.*

interface UserEndpoint {

    @GET("user/{id_user}")
    fun getProfile(
        @Path("id_user")id_user: String
    ): Call<ProfileResponse>

    @GET("anak")
    fun getAnak(
        @Query("id_user") id_user: String
    ): Call<AnakResponse>



    @FormUrlEncoded
    @PUT("anak/is_ready/{id_anak}")
    fun siap(
        @Path("id_anak") id_anak: String,
        @Field("is_ready") is_ready: Int
    ): Call<SiapResponse>


//    @FormUrlEncoded
//    @Headers("Accept: application/json")
//    @PUT("ready/{id_anak}")
//    fun is_ready(
//        @Path("id_anak") id_anak: String,
//        @Field("is_ready") is_ready: Int
//    ): Call<SiapResponse>
}