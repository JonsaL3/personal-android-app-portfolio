package com.gonzaloracergalan.portfolio.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzaloracergalan.portfolio.ui.state.GetCurrentInformacionGeneralState
import com.gonzaloracergalan.portfolio.ui.viewmodel.InformacionGeneralViewModel

@Composable
fun InformacionGeneralScreen(
    paddingValues: PaddingValues
) {
    val viewModel: InformacionGeneralViewModel = viewModel<InformacionGeneralViewModel>()
    val informacionGeneral by viewModel.getCurrentInformacionGeneralState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        when (informacionGeneral) {
            is GetCurrentInformacionGeneralState.Loading -> {
                Text(text = "Loading")
            }
            is GetCurrentInformacionGeneralState.Success -> {
                val m = informacionGeneral as GetCurrentInformacionGeneralState.Success
                Text(text = m.data.nombre)
                Text(text = m.data.correo)
                //Text(text = m.data.imagen)
                Text(text = m.data.resumen)
                Text(text = m.data.telefono)
                Text(text = m.data.cargoActual)
            }
            is GetCurrentInformacionGeneralState.Error -> {
                val m = informacionGeneral as GetCurrentInformacionGeneralState.Error
                Text(text = "Error ${m.error}")
            }
            GetCurrentInformacionGeneralState.NotData -> {
                Text(text = "Not Data")
            }
        }
    }
}