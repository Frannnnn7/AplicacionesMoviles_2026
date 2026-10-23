package com.example.franciscabenitez_tm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import com.example.franciscabenitez_tm.ui.theme.FranciscaBenitez_TMTheme

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




                    }

                }

            }

        }

    }

}