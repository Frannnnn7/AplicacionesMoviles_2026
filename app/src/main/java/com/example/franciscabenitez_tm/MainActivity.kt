package com.example.franciscabenitez_tm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color.Companion.Red
import com.example.franciscabenitez_tm.ui.theme.FranciscaBenitez_TMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FranciscaBenitez_TMTheme {
                var nombre by remember { mutableStateOf("") }

                var modal by remember { mutableStateOf(false) }
                fun activarModal() {modal = true}

                var terminos by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {nombre = it},
                        label = {Text("Ingresa nombre")},
                        isError = true,
                        supportingText = {Text("Ingrese un nombre valido", color = Red)}
                    )

                    Checkbox(
                        checked = terminos,
                        onCheckedChange = {terminos = it}
                    )

                    Button(onClick = {activarModal()}) {
                        Text(text = "Enviar")
                    }
                }

                if(modal) {
                    AlertDialog(
                        onDismissRequest = { },
                        title = { Text("Confirmación") },
                        text = { Text("Formulario enviado correctamente") },
                        confirmButton = {
                            Button(onClick = { }) { Text("OK") }
                        }
                    )
                }
            }
        }
    }
}
