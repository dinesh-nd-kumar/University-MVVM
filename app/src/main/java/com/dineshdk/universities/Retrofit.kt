package com.dineshdk.universities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Retrofit {

    val api:UniversityApi by lazy {
        Retrofit.Builder().baseUrl("http://universities.hipolabs.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversityApi::class.java)
    }
}