package com.example.franciscabenitez_tm.repository

package com.example.franciscabenitez_tm.data.repository

import com.example.franciscabenitez_tm.data.remote.AuthResponse
import com.example.franciscabenitez_tm.data.remote.FraseRequest
import com.example.franciscabenitez_tm.data.remote.LoginRequest
import com.example.franciscabenitez_tm.data.remote.SignupRequest
import com.example.franciscabenitez_tm.data.remote.XanoClient

class XanoRepository {
    suspend fun login(email: String, password: String): AuthResponse {
        val response = XanoClient.auth.login(LoginRequest(usuario, password))
        if (!response.isSuccessful) error("Error de inicio de sesión (${response.code()})")
        return response.body() ?: error("Respuesta vacía de Xano")
    }

    suspend fun signup(name: String, email: String, password: String): AuthResponse {
        val response = XanoClient.auth.signup(SignupRequest(tipodecuenta, name, email, password))
        if (!response.isSuccessful) error("Error de registro (${response.code()})")
        return response.body() ?: error("Respuesta vacía de Xano")
    }

    suspend fun listarFrases() = XanoClient.frases.listar()
    suspend fun obtenerFrase(id: Long) = XanoClient.frases.obtener(id)
    suspend fun crearFrase(request: FraseRequest) = XanoClient.frases.crear(request)
    suspend fun editarFrase(id: Long, request: FraseRequest) = XanoClient.frases.editar(id, request)
    suspend fun eliminarFrase(id: Long) = XanoClient.frases.eliminar(id)
}