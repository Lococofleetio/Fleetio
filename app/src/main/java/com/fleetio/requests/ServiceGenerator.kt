package com.fleetio.requests

import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

//Create a single point of entry for retrofit API calls
object ServiceGenerator {

    private const val BASE_URL ="https://secure.fleetio.com/"

    private const val token = "Token token=6503cd0340f7976814e217dbc9cb8786b25087ec"
    private const val tokenId ="798819214b"

    //intercept request from retrofit and add parameters
    private val interceptor = Interceptor { chain ->

        val request = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .addHeader("Account-Token",tokenId)

        chain?.proceed(request?.build())
    }

    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder().also {
            it.addInterceptor(interceptor)
            it.addNetworkInterceptor(SimpleLoggingInterceptor())
        }.build()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
    private val retrofit = retrofitBuilder.build()

    val requestApi: RequestApi = retrofit.create(RequestApi::class.java)
}