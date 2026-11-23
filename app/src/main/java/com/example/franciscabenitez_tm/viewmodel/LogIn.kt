
package com.example.franciscabenitez_tm.viewmodel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.franciscabenitez_tm.R
import com.example.franciscabenitez_tm.model.UsuarioState
import com.example.franciscabenitez_tm.repository.AuthViewModel

@Composable
fun LogIn(
    modifier: Modifier = Modifier,
    onContinuar: (UsuarioState) -> Unit
) {
    val authViewModel: AuthViewModel = viewModel()

    var tipoCuenta by remember { mutableStateOf("Cliente") }
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarExitoDialog by remember { mutableStateOf(false) }

    val esFormularioValido =
        usuario.isNotBlank() && contrasena.isNotBlank()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icono),
            contentDescription = "Logo Fumigapp",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp)
                .size(56.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Ingresar como:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = tipoCuenta == "Cliente",
                    onClick = { tipoCuenta = "Cliente" },
                    label = { Text("Cliente") },
                    modifier = Modifier.weight(1f)
                )

                FilterChip(
                    selected = tipoCuenta == "Prestador de servicio",
                    onClick = { tipoCuenta = "Prestador de servicio" },
                    label = { Text("Prestador de servicio") },
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            authViewModel.error.value?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = {
                    authViewModel.login(
                        usuario.trim(),
                        contrasena
                    ) {
                        mostrarExitoDialog = true
                    }
                },
                enabled = esFormularioValido &&
                        !authViewModel.cargando.value,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32)
                )
            ) {
                Text(
                    text = if (authViewModel.cargando.value) {
                        "Ingresando..."
                    } else {
                        "Continuar"
                    },
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (mostrarExitoDialog) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        text = "¡Sesión iniciada con éxito!",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            mostrarExitoDialog = false
                            onContinuar(
                                UsuarioState(
                                    usuario = usuario.trim(),
                                    tipoCuenta = tipoCuenta
                                )
                            )
                        }
                    ) {
                        Text(
                            text = "Continuar",
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    }
}
