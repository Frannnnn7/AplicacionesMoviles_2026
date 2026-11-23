
package com.example.franciscabenitez_tm.data.remote

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val usuario: String,
    val password: String
)

data class SignupRequest(
    val tipodecuenta: String,
    val name: String,
    val usuario: String,
    val fechadenacimiento: String,
    val direccion: String,
    val numerodetelefono: String,
    val password: String
)

data class AuthResponse(
    @SerializedName("authToken")
    val authToken: String? = null,
    @SerializedName("user_id")
    val userId: Long? = null
)

data class FraseRequest(
    val nombre: String,
    val edad: Int,
    val frase: String,
    val imagen: JsonObject? = null
)

data class FraseResponse(
    val id: Long,
    val created_at: Long? = null,
    val nombre: String? = null,
    val edad: Int? = null,
    val frase: String? = null,
    val imagen: JsonObject? = null
)