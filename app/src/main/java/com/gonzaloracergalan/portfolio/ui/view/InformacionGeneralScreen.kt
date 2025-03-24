package com.gonzaloracergalan.portfolio.ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzaloracergalan.portfolio.R
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
                .padding(paddingValues),
            verticalArrangement = Arrangement.Bottom,
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
    var isMostrarMas by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 40.sp,
            text = informacionGeneral.nombre.uppercase(),
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            text = informacionGeneral.cargoActual,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 32.sp,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = if (isMostrarMas) {
                Modifier
                    .verticalScroll(scrollState)
                    .weight(1f)
                    .fillMaxWidth()
            } else {
                Modifier.fillMaxWidth()
            },
            fontSize = 14.sp,
            text = informacionGeneral.resumen,
            color = GrisTextos, // todo vincular al tema
            lineHeight = 18.sp,
            maxLines = if (isMostrarMas) Int.MAX_VALUE else 6,
        )
        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                isMostrarMas = !isMostrarMas
            }
        ) {
            Text(
                color = MaterialTheme.colorScheme.onBackground,
                text = if (!isMostrarMas) {
                    stringResource(R.string.informaciongeneralscreen_mostrarmas)
                } else {
                    stringResource(R.string.informaciongeneralscreen_mostrarmenos)
                }
            )
        }
    }
}