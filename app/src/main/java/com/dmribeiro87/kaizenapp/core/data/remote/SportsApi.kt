package com.dmribeiro87.kaizenapp.core.data.remote

import com.dmribeiro87.kaizenapp.core.data.remote.model.SportResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface SportsApi {

    @GET("sports")
    suspend fun getSportsEvents(): Response<List<SportResponseItem>>

}