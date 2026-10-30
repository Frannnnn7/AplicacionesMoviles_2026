package com.example.franciscabenitez_tm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var pantalla by remember { mutableStateOf("inicio") }
                var usuarioLogueado by remember { mutableStateOf(UsuarioState()) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (pantalla) {
                        "inicio" -> Inicio(
                            modifier = Modifier.padding(innerPadding),
                            login = { pantalla = "login" },
                            singup = { pantalla = "singup" }
                        )
                        "singup" -> SingUp(
                            modifier = Modifier.padding(innerPadding),
                            onCrearCuenta = { datosUsuario ->
                                usuarioLogueado = datosUsuario
                                pantalla = if (datosUsuario.tipoCuenta == "Cliente") {
                                    "pagprincipal_cli"
                                } else {
                                    "pagprincipal_pres"
                                }
                            }
                        )
                        "login" -> LogIn(
                            modifier = Modifier.padding(innerPadding),
                            onContinuar = { datosUsuario ->
                                usuarioLogueado = datosUsuario
                                pantalla = if (datosUsuario.tipoCuenta == "Cliente") {
                                    "pagprincipal_cli"
                                } else {
                                    "pagprincipal_pres"
                                }
                            }
                        )
                        "pagprincipal_cli" -> PagPrincipal_Cli(
                            modifier = Modifier.padding(innerPadding),
                            usuarioData = usuarioLogueado,
                            onPerfilClick = { pantalla = "perfil" }
                        )
                        "pagprincipal_pres" -> PagPrincipal_Pres(
                            modifier = Modifier.padding(innerPadding),
                            usuarioData = usuarioLogueado,
                            onPerfilClick = { pantalla = "perfil" }
                        )
                        "perfil" -> Perfil(
                            modifier = Modifier.padding(innerPadding),
                            usuarioData = usuarioLogueado,
                            onEditarClick = { pantalla = "editar_perfil" },
                            onVolver = {
                                pantalla = if (usuarioLogueado.tipoCuenta == "Cliente") "pagprincipal_cli" else "pagprincipal_pres"
                            },
                            onCerrarSesion = {
                                usuarioLogueado = UsuarioState()
                                pantalla = "inicio"
                            }
                        )
                        "editar_perfil" -> EditarPerfil(
                            modifier = Modifier.padding(innerPadding),
                            usuarioActual = usuarioLogueado,
                            onGuardarCambios = { usuarioEditado ->
                                usuarioLogueado = usuarioEditado
                                pantalla = "perfil"
                            },
                            onCancelar = { pantalla = "perfil" }
                        )
                    }
                }
            }
        }
    }
}