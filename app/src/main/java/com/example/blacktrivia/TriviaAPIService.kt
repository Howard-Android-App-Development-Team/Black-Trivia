package com.example.blacktrivia

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TriviaAPIService {
    @GET("api.php")
    fun getTriviaQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String?,
        @Query("type") type: String?
    ): Call<TriviaResponse?>?
}
