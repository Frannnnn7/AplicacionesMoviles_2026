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
import com.example.franciscabenitez_tm.data.repository.SesionManager


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sesionManager = SesionManager(this)


        setContent {
            MaterialTheme {


                var usuarioLogueado by remember {
                    mutableStateOf(
                        if (sesionManager.sesionIniciada()) {
                            sesionManager.obtenerUsuario()
                        } else {
                            UsuarioState()
                        }
                    )
                }


                var pantalla by remember {
                    mutableStateOf(
                        if (sesionManager.sesionIniciada()) {
                            if (usuarioLogueado.tipoCuenta == "Cliente") {
                                "pagprincipal_cli"
                            } else {
                                "pagprincipal_pres"
                            }
                        } else {
                            "inicio"
                        }
                    )
                }


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
                                sesionManager.guardarSesion(datosUsuario)


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
                                sesionManager.guardarSesion(datosUsuario)


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
                                pantalla = if (usuarioLogueado.tipoCuenta == "Cliente") {
                                    "pagprincipal_cli"
                                } else {
                                    "pagprincipal_pres"
                                }
                            },
                            onCerrarSesion = {
                                sesionManager.cerrarSesion()
                                usuarioLogueado = UsuarioState()
                                pantalla = "inicio"
                            }
                        )


                        "editar_perfil" -> EditarPerfil(
                            modifier = Modifier.padding(innerPadding),
                            usuarioActual = usuarioLogueado,
                            onGuardarCambios = { usuarioEditado ->
                                usuarioLogueado = usuarioEditado
                                sesionManager.guardarSesion(usuarioEditado)
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
