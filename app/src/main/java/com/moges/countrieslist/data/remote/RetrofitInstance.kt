package com.moges.countrieslist.data.remote

import com.google.gson.GsonBuilder
import com.moges.countrieslist.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitInstance {

    // Base url for the Countries fetching API
    private const val COUNTRIES_API_BASE_URL =
        "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        // Log API request data only for debug mode
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    // We can set timeouts to our requests here
    // also to make our app more resilient to network issues
    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val gson by lazy {
        GsonBuilder().create()
    }

    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(COUNTRIES_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val countriesService: CountriesService = retrofit.create(CountriesService::class.java)
}