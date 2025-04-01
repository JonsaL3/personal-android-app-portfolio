package com.gonzaloracergalan.portfolio.ui.view.screen

import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzaloracergalan.portfolio.R
import com.gonzaloracergalan.portfolio.ui.model.ExperienciaModel
import com.gonzaloracergalan.portfolio.ui.state.ExperienciaState
import com.gonzaloracergalan.portfolio.ui.theme.GrisTextos
import com.gonzaloracergalan.portfolio.ui.viewmodel.ExperienciaViewModel

@Composable
fun ExperienciaScreen(
    paddingValues: PaddingValues
) {
    val viewModel: ExperienciaViewModel = viewModel<ExperienciaViewModel>()
    val state = viewModel.experienciaState.collectAsStateWithLifecycle()

    //BoxWithBackgroundAnimatedImage(Modifier.fillMaxSize()) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // todo tratar todos los estados
        if (state.value is ExperienciaState.Idle) {
            (state.value as ExperienciaState.Idle).data.apply {
                if (trabajos.isEmpty() && voluntariados.isEmpty()) {
                    Text("Not implemented or not data... si estamos en esta pantalla si o si debe haber algo, pero hay que comprobarlo TODO..") // todo
                } else {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .padding(paddingValues)
                    ) {
                        if (trabajos.isNotEmpty()) {
                            item {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 40.sp,
                                    text = stringResource(R.string.experienciascreen_trabajos).uppercase(),
                                    color = MaterialTheme.colorScheme.primary,
                                    lineHeight = 48.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            items(trabajos) {
                                TrabajoItem(it)
                                if (it != trabajos.last()) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        }
                        if (trabajos.isNotEmpty() && voluntariados.isNotEmpty()) {
                            item { Spacer(modifier = Modifier.height(16.dp)) }
                        }
                        if (voluntariados.isNotEmpty()) {
                            item {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 40.sp,
                                    text = stringResource(R.string.experienciascreen_voluntariados).uppercase(),
                                    color = MaterialTheme.colorScheme.primary,
                                    lineHeight = 48.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        } else if (state.value is ExperienciaState.Loading) {
            // todo
        } else if (state.value is ExperienciaState.Error) {
            // todo
        }
    }
}

@Composable
private fun TrabajoItem(item: ExperienciaModel.Trabajo) {
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)
    ) {
        TimeLineItem(true, true)
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            item.posicion?.let { posicion ->
                Text(
                    text = posicion,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            item.nombre?.let { nombre ->
                Text(
                    text = nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                if (item.fechaInicio != null && item.fechaFin != null) {
                    Text(
                        text = "${item.getFormattedFecha()}",
                        fontSize = 10.sp,
                        color = GrisTextos // todo theme
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item.resumen?.let { resumen ->
                Text(
                    text = resumen,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item.url?.let { url ->
                if (Patterns.WEB_URL.matcher(url).matches()) {
                    val context = LocalContext.current
                    Text(
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply { data = url.toUri() }
                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(
                                    context,
                                    R.string.experienciascreen_noactivityparaabrirenlace,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        text = url,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                } else {
                    Text(
                        text = url,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                        color = GrisTextos // TODO THEME
                    )
                }
            } seguir con la linea de tiempo, recordar diseño con lineas verdes al lado de la experiencia, y el hueco entre cards tenerlo en cuenta.
        }
    }
}

@Composable
private fun TimeLineItem(
    isTopLine: Boolean,
    isBottomLine: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isTopLine) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primary),
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)
            )
        }
        if (isBottomLine) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primary),
            )
        }
    }
}

@Composable
@Preview
fun PreviewExperienciaScreen() {
    TimeLineItem(true, true)
}