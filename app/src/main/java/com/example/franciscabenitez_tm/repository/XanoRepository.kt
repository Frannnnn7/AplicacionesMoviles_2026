
package com.example.franciscabenitez_tm.repository

import com.example.franciscabenitez_tm.data.remote.AuthResponse
import com.example.franciscabenitez_tm.data.remote.FraseRequest
import com.example.franciscabenitez_tm.data.remote.LoginRequest
import com.example.franciscabenitez_tm.data.remote.SignupRequest
import com.example.franciscabenitez_tm.data.remote.XanoClient

class XanoRepository {

    suspend fun login(
        usuario: String,
        password: String
    ): AuthResponse {
        val response = XanoClient.auth.login(
            LoginRequest(usuario, password)
        )


        if (!response.isSuccessful) {
            val detalle = response.errorBody()?.string()
            error("Error de inicio de sesión (${response.code()}): $detalle")
        }


        return response.body()
            ?: error("Respuesta vacía de Xano")
    }

    suspend fun signup(
        tipoCuenta: String,
        nombre: String,
        usuario: String,
        fechaNacimiento: String,
        direccion: String,
        telefono: String,
        password: String
    ): AuthResponse {
        val response = XanoClient.auth.signup(
            SignupRequest(
                tipodecuenta = tipoCuenta,
                name = nombre,
                usuario = usuario,
                fechadenacimiento = fechaNacimiento,
                direccion = direccion,
                numerodetelefono = telefono,
                password = password
            )
        )


        if (!response.isSuccessful) {
            val detalle = response.errorBody()?.string()
            error("Error de registro (${response.code()}): $detalle")
        }


        return response.body()
            ?: error("Respuesta vacía de Xano")
    }

    suspend fun listarFrases() =
        XanoClient.frases.listar()

    suspend fun obtenerFrase(id: Long) =
        XanoClient.frases.obtener(id)

    suspend fun crearFrase(request: FraseRequest) =
        XanoClient.frases.crear(request)

    suspend fun editarFrase(id: Long, request: FraseRequest) =
        XanoClient.frases.editar(id, request)

    suspend fun eliminarFrase(id: Long) =
        XanoClient.frases.eliminar(id)
}
