package com.app.compose.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(onClick = { onDismiss() }) {
                            Text(
                                text = actionLabel,
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            ) {
                Text(
                    text = data.message,
                    style = MaterialTheme.typography
                        .body2,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    )
}