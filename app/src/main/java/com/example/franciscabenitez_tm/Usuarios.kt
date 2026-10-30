package com.example.franciscabenitez_tm

data class UsuarioState(
    val nombre: String = "",
    val usuario: String = "",
    val nacimiento: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val tipoCuenta: String = "Cliente",
    val contrasena: String = ""
)