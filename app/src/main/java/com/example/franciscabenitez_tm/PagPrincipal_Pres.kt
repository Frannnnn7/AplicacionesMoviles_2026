package com.example.franciscabenitez_tm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class SolicitudCliente(
    val id: Int,
    val cliente: String,
    val predio: String,
    val ubicacion: String,
    val distanciaKm: Int,
    val hectareas: Int,
    val presupuestoTotal: String
)

@Composable
fun PagPrincipal_Pres(
    modifier: Modifier = Modifier,
    onPerfilClick: () -> Unit
) {
    val ubicacionPrestador = "Curicó, Región del Maule"

    val solicitudesSimuladas = listOf(
        SolicitudCliente(
            id = 1,
            cliente = "Carlos Sainz",
            predio = "Agrícola Los Olivos",
            ubicacion = "Curicó, Región del Maule",
            distanciaKm = 8,
            hectareas = 25,
            presupuestoTotal = "$1.200.000"
        ),
        SolicitudCliente(
            id = 2,
            cliente = "Kimi Räikkönen",
            predio = "Fundo Santa Elena",
            ubicacion = "Molina, Región del Maule",
            distanciaKm = 18,
            hectareas = 40,
            presupuestoTotal = "$1.920.000"
        ),
        SolicitudCliente(
            id = 3,
            cliente = "Sebastian Vettel",
            predio = "Frutícola San Pedro",
            ubicacion = "Teno, Región del Maule",
            distanciaKm = 22,
            hectareas = 15,
            presupuestoTotal = "$750.000"
        ),
        SolicitudCliente(
            id = 4,
            cliente = "Daniel Ricciardo",
            predio = "Agrícola El Manzano",
            ubicacion = "Talca, Región del Maule",
            distanciaKm = 65,
            hectareas = 60,
            presupuestoTotal = "$2.700.000"
        ),
        SolicitudCliente(
            id = 5,
            cliente = "Valtteri Bottas",
            predio = "Viña Valle Verde",
            ubicacion = "Rancagua, Región de O'Higgins",
            distanciaKm = 105,
            hectareas = 30,
            presupuestoTotal = "$1.500.000"
        )
    ).sortedBy { it.distanciaKm }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icono),
                    contentDescription = "Logo Fumigapp",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Fumigapp",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
                }
            }

            IconButton(
                onClick = onPerfilClick,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0))
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto de perfil",
                    tint = Color(0xFF757575),
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFE8F5E9))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = "Base de operaciones:",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF2E7D32)
                    )
                    Text(
                        text = ubicacionPrestador,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Solicitudes cerca de ti",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(solicitudesSimuladas) { solicitud ->
                TarjetaSolicitud(solicitud = solicitud)
            }
        }
    }

@Composable
fun TarjetaSolicitud(solicitud: SolicitudCliente) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = solicitud.cliente,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )
                    Text(
                        text = solicitud.predio,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "A ${solicitud.distanciaKm} km",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${solicitud.ubicacion} • ${solicitud.hectareas} ha",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Presupuesto",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = solicitud.presupuestoTotal,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF2E7D32)
                    )
                }

                Button(
                    onClick = { /* Acción futura para aceptar trabajo */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D32)
                    )
                ) {
                    Text("Aceptar")
                }
            }
        }
    }
}