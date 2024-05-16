package net.asgeri.atlmvvm.api

import net.asgeri.atlmvvm.model.TodoResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface TodoApi {

    @GET("todos")
    fun getTodoApi(): Call<TodoResponseModel>

}