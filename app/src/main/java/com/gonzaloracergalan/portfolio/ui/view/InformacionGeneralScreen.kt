package com.gonzaloracergalan.portfolio.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzaloracergalan.portfolio.ui.model.InformacionGeneralUI
import com.gonzaloracergalan.portfolio.ui.state.GetCurrentInformacionGeneralState
import com.gonzaloracergalan.portfolio.ui.theme.GrisTextos
import com.gonzaloracergalan.portfolio.ui.view.component.BoxWithBackgroundAnimatedImage
import com.gonzaloracergalan.portfolio.ui.viewmodel.InformacionGeneralViewModel

@Composable
fun InformacionGeneralScreen(
    paddingValues: PaddingValues,
) {
    val viewModel: InformacionGeneralViewModel = viewModel<InformacionGeneralViewModel>()
    val informacionGeneral by viewModel.getCurrentInformacionGeneralState.collectAsStateWithLifecycle()

    BoxWithBackgroundAnimatedImage(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (informacionGeneral) {
                is GetCurrentInformacionGeneralState.Loading -> {
                    Text(text = "Loading")
                }

                is GetCurrentInformacionGeneralState.Success -> {
                    val mInformacionGeneral =
                        informacionGeneral as GetCurrentInformacionGeneralState.Success
                    OnSuccessInformacionGeneral(mInformacionGeneral.data)
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
}

@Composable
private fun OnSuccessInformacionGeneral(
    informacionGeneral: InformacionGeneralUI
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(280.dp))
        Text(
            fontSize = 40.sp,
            text = informacionGeneral.nombre.uppercase(),
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            fontSize = 24.sp,
            text = informacionGeneral.cargoActual,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 32.sp,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            fontSize = 14.sp,
            text = informacionGeneral.resumen,
            color = GrisTextos, // todo vincular al tema
            lineHeight = 18.sp,
        )
        problema de las imagenes y si el texto ultimo es muy largo pintar coso verde de mostrar mas
                al darle a mostrar mas se pinta algo de negro y se emborrosiona el fondo
    }
}