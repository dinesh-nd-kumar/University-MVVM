package com.dineshdk.universities

import retrofit2.Response
import retrofit2.http.GET

interface UniversityApi {
    @GET("/search?country=india")
    suspend fun getUniversityList():Response<List<University>>

}