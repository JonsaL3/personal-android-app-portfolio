package com.gonzaloracergalan.portfolio.ui.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TransparentCircledTopBar(
    modifier: Modifier = Modifier,
    onHamburguerClicked: () -> Unit,
    onPhoneNumerClicked: (() -> Unit)? = null,
    onEmailClicked: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        HamburguerButton(onHamburguerClicked)
        Spacer(Modifier.weight(1f))
        ContactButtons(
            onPhoneNumerClicked = onPhoneNumerClicked,
            onEmailClicked = onEmailClicked
        )
    }
}

@Composable
private fun HamburguerButton(onHamburguerClicked: () -> Unit) {
    CircularIconButton(
        modifier = Modifier.size(64.dp),
        icon = Icons.Outlined.Menu,
        borderColor = MaterialTheme.colorScheme.onBackground,
        onClick = onHamburguerClicked
    )
}

@Composable
private fun ContactButtons(
    onPhoneNumerClicked: (() -> Unit)? = null,
    onEmailClicked: (() -> Unit)? = null,
) {
    Row {
        onPhoneNumerClicked?.let {
            CircularIconButton(
                modifier = Modifier.size(64.dp),
                icon = Icons.Outlined.Phone,
                borderColor = MaterialTheme.colorScheme.onBackground,
                onClick = it
            )
        }
        if (onPhoneNumerClicked != null && onEmailClicked != null) {
            Spacer(Modifier.size(8.dp))
        }
        onEmailClicked?.let {
            CircularIconButton(
                modifier = Modifier.size(64.dp),
                icon = Icons.Outlined.Mail,
                borderColor = MaterialTheme.colorScheme.onBackground,
                onClick = it
            )
        }
    }
}

@Preview
@Composable
fun PreviewTransparentCircledTopBar() {
    TransparentCircledTopBar(
        onHamburguerClicked = {},
        onPhoneNumerClicked = {},
        onEmailClicked = {}
    )
}