package com.scrollupstudio.kesekolah.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {

    var BASE_URL: String = "http://kesekolah.scrollupstudio.com/api/"
//    var BASE_URL: String = "http://192.168.43.66/kesekolah/api/"

    val auth: AuthEndpoint
        get() {
            return retrofit.create(AuthEndpoint::class.java)
        }

    val user: UserEndpoint
        get() {
            return retrofit.create(UserEndpoint::class.java)
        }

    val driver: DriverEndpoint
        get() {
            return retrofit.create(DriverEndpoint::class.java)
        }

    val retrofit: Retrofit
    get() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}