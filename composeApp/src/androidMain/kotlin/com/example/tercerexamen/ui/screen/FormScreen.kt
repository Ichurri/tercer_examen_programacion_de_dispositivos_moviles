package com.example.tercerexamen.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tercerexamen.ui.viewmodel.FormViewModel

@Composable
fun FormScreen(context: Context) {
    val viewModel = remember { FormViewModel(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Formulario con Autosave",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = viewModel.formData.value,
            onValueChange = { viewModel.formData.value = it },
            label = { Text("Escribe algo...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 5
        )

        Text(
            text = "Se guarda automáticamente cada 30 segundos",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.submitForm() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar formulario")
        }

        Button(
            onClick = { viewModel.clearForm() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Limpiar")
        }
    }
}

