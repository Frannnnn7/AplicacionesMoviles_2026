
package com.example.franciscabenitez_tm.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): Response<AuthResponse>
}

interface FrasesApi {

    @GET("frase")
    suspend fun listar(): Response<List<FraseResponse>>

    @GET("frase/{frase_id}")
    suspend fun obtener(
        @Path("frase_id") id: Long
    ): Response<FraseResponse>

    @POST("frase")
    suspend fun crear(
        @Body request: FraseRequest
    ): Response<FraseResponse>

    @PATCH("frase/{frase_id}")
    suspend fun editar(
        @Path("frase_id") id: Long,
        @Body request: FraseRequest
    ): Response<FraseResponse>

    @DELETE("frase/{frase_id}")
    suspend fun eliminar(
        @Path("frase_id") id: Long
    ): Response<Unit>
}
