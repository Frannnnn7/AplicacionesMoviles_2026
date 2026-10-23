package com.example.franciscabenitez_tm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.franciscabenitez_tm.ui.theme.FranciscaBenitez_TMTheme

@Composable
fun Inicio (
    modifier: Modifier = Modifier,
    login: () -> Unit,
    singup: () -> Unit

){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fumigapp",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Empezamos creando una cuenta")

        Spacer(modifier = Modifier.height(24.dp))

        Column (
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Button(onClick = login) {
                Text("Iniciar sesión")
            }

            Text("¿Ya tienes una cuenta?")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(onClick = singup) {
                Text("Registrate")
            }
        }
    }
}