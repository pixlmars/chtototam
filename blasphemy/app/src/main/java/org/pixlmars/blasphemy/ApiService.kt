package org.pixlmars.blasphemy

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("random/anime")
    suspend fun getRandomAnime(): Response<RandomAnime>
}