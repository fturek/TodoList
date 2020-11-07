package com.fturek.todolist.api.remote

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("URL_PLACEHOLDER")
    fun get(): Single<Unit>

}