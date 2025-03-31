package com.gonzaloracergalan.portfolio.ui.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzaloracergalan.portfolio.R
import com.gonzaloracergalan.portfolio.ui.model.ExperienciaModel
import com.gonzaloracergalan.portfolio.ui.state.ExperienciaState
import com.gonzaloracergalan.portfolio.ui.view.component.BoxWithBackgroundAnimatedImage
import com.gonzaloracergalan.portfolio.ui.viewmodel.ExperienciaViewModel

@Composable
fun ExperienciaScreen(
    paddingValues: PaddingValues
) {
    val viewModel: ExperienciaViewModel = viewModel<ExperienciaViewModel>()
    val state = viewModel.experienciaState.collectAsStateWithLifecycle()

    //BoxWithBackgroundAnimatedImage(Modifier.fillMaxSize()) {
    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // todo tratar todos los estados
        if (state.value is ExperienciaState.Idle) {
            (state.value as ExperienciaState.Idle).data.apply {
                if (trabajos.isEmpty() && voluntariados.isEmpty()) {
                    Text("Not implemented or not data... si estamos en esta pantalla si o si debe haber algo, pero hay que comprobarlo TODO..") // todo
                } else {
                    Column(Modifier.fillMaxSize().padding(paddingValues)) {
                        if (trabajos.isNotEmpty()) {
                            Trabajos(trabajos)
                        }
                        if (trabajos.isNotEmpty() && voluntariados.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        if (voluntariados.isNotEmpty()) {
                            Voluntariados(voluntariados)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Trabajos(trabajos: List<ExperienciaModel.Trabajo>) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        fontSize = 40.sp,
        text = stringResource(R.string.experienciascreen_trabajos).uppercase(),
        color = MaterialTheme.colorScheme.primary,
        lineHeight = 48.sp,
        fontWeight = FontWeight.Bold
    )
    LazyColumn(Modifier.fillMaxWidth()) {
        items(trabajos) {

        }
    }
}

@Composable
private fun Voluntariados(voluntariados: List<ExperienciaModel.Voluntariado>) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        fontSize = 40.sp,
        text = stringResource(R.string.experienciascreen_voluntariados).uppercase(),
        color = MaterialTheme.colorScheme.primary,
        lineHeight = 48.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview
fun PreviewExperienciaScreen() {
    ExperienciaScreen(
        paddingValues = PaddingValues(8.dp)
    )
}