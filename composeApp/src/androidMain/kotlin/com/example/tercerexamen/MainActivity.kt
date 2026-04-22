package com.example.tercerexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tercerexamen.service.AppEventTracker
import com.example.tercerexamen.util.WorkManagerHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Inicializar rastreador de eventos
        AppEventTracker.getInstance(this)

        // Programar workers de sincronización
        WorkManagerHelper.scheduleSyncWorkers(this)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}