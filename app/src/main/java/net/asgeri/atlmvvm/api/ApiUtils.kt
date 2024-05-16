package net.asgeri.atlmvvm.api

class ApiUtils {
    companion object {
        fun getApi(): TodoApi {
            return RetrofitClient.getRetrofit().create(TodoApi::class.java)
        }
    }
}