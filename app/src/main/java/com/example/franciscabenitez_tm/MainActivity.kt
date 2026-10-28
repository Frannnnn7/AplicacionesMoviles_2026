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

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (pantalla) {
                        "inicio" -> Inicio(
                            modifier = Modifier.padding(innerPadding),
                            login = { pantalla = "login" },
                            singup = { pantalla = "singup" }
                        )
                        "singup" -> SingUp(
                            modifier = Modifier.padding(innerPadding),
                            onCrearCuenta = { pantalla = "inicio" }
                        )
                        "login" -> LogIn(
                            modifier = Modifier.padding(innerPadding),
                            onContinuar = { pantalla = "inicio" }
                        )
                    }
                }
            }
        }
    }
}