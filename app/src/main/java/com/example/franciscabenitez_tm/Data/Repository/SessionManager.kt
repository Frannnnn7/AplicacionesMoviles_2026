package com.example.franciscabenitez_tm.data.repository


import android.content.Context
import com.example.franciscabenitez_tm.UsuarioState


class SesionManager(context: Context) {


    private val preferencias = context.getSharedPreferences(
        "FumigAppSesion",
        Context.MODE_PRIVATE
    )

    fun guardarSesion(datos: UsuarioState) {
        preferencias.edit()
            .putBoolean("sesionActiva", true)
            .putString("nombre", datos.nombre)
            .putString("usuario", datos.usuario)
            .putString("nacimiento", datos.nacimiento)
            .putString("direccion", datos.direccion)
            .putString("telefono", datos.telefono)
            .putString("tipoCuenta", datos.tipoCuenta)
            .apply()
    }

    fun sesionIniciada(): Boolean {
        return preferencias.getBoolean("sesionActiva", false)
    }

    fun obtenerUsuario(): UsuarioState {
        return UsuarioState(
            nombre = preferencias.getString("nombre", "") ?: "",
            usuario = preferencias.getString("usuario", "") ?: "",
            nacimiento = preferencias.getString("nacimiento", "") ?: "",
            direccion = preferencias.getString("direccion", "") ?: "",
            telefono = preferencias.getString("telefono", "") ?: "",
            tipoCuenta = preferencias.getString("tipoCuenta", "Cliente") ?: "Cliente"
        )
    }

    fun cerrarSesion() {
        preferencias.edit().clear().apply()
    }
}
