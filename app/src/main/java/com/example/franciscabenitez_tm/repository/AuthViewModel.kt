
package com.example.franciscabenitez_tm.repository

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = XanoRepository()

    var cargando = mutableStateOf(false)
        private set

    var error = mutableStateOf<String?>(null)
        private set

    var token = mutableStateOf<String?>(null)
        private set

    fun login(
        usuario: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        ejecutar(onSuccess) {
            repository.login(usuario, password).authToken
        }
    }

    fun signup(
        tipoCuenta: String,
        nombre: String,
        usuario: String,
        fechaNacimiento: String,
        direccion: String,
        telefono: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        ejecutar(onSuccess) {
            repository.signup(
                tipoCuenta,
                nombre,
                usuario,
                fechaNacimiento,
                direccion,
                telefono,
                password
            ).authToken
        }
    }

    private fun ejecutar(
        onSuccess: () -> Unit,
        solicitud: suspend () -> String?
    ) {
        viewModelScope.launch {
            cargando.value = true
            error.value = null

            try {
                val tokenRecibido = solicitud()

                if (tokenRecibido.isNullOrBlank()) {
                    throw IllegalStateException(
                        "Xano no devolvió un token"
                    )
                }

                token.value = tokenRecibido
                onSuccess()

            } catch (e: Exception) {
                error.value = e.message
                    ?: "No se pudo conectar con Xano"
            } finally {
                cargando.value = false
            }
        }
    }
}
