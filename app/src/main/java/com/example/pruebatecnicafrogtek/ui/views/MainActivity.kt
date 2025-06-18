package com.example.pruebatecnicafrogtek.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.pruebatecnicafrogtek.R
import com.example.pruebatecnicafrogtek.ui.theme.PruebaTecnicaFrogtekTheme
import com.example.pruebatecnicafrogtek.ui.views.composables.screens.HomeScreenComposeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaTecnicaFrogtekTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(
                            stringResource(R.string.title_app),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    })
                }) { innerPadings ->
                    HomeScreenComposeView(
                        mainViewModel = mainViewModel,
                        modifier = Modifier.padding(innerPadings)
                    )
                }
            }
        }
    }
}