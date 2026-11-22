package com.example.franciscabenitez_tm.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.franciscabenitez_tm.repository.XanoRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = XanoRepository()
    var cargando = androidx.compose.runtime.mutableStateOf(false)
        private set
    var error = androidx.compose.runtime.mutableStateOf<String?>(null)
        private set
    var token = androidx.compose.runtime.mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        ejecutar(onSuccess) { repository.Login(email, password).authToken }
    }

    fun signup(name: String, email: String, password: String, onSuccess: () -> Unit) {
        ejecutar(onSuccess) { repository.Signup(name, email, password).authToken }
    }

    private fun ejecutar(onSuccess: () -> Unit, solicitud: suspend () -> String?) {
        viewModelScope.launch {
            cargando.value = true
            error.value = null
            try {
                token.value = solicitud() ?: kotlin.error("Xano no devolvió un token")
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "No se pudo conectar con Xano"
            } finally {
                cargando.value = false
            }
        }
    }
}