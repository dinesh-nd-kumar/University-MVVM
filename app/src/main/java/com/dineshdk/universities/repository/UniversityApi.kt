package com.dineshdk.universities.repository

import com.dineshdk.universities.models.University
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApi {
    @GET("/search?")
    suspend fun getUniversityList(@Query("country") country : String ):Response<List<University>>

}