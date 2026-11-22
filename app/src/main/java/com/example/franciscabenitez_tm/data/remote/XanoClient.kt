package com.example.franciscabenitez_tm.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object XanoClient {
    private fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val auth: AuthApi = retrofit(
        "https://x8ki-letl-twmt.n7.xano.io/api:_D8aanwl/"
    ).create(AuthApi::class.java)

    val frases: FrasesApi = retrofit(
        "https://x8ki-letl-twmt.n7.xano.io/api:NJT47sQr/"
    ).create(FrasesApi::class.java)
}