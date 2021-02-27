package com.app.compose.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp


@Suppress("Deprecation")
@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
) {
    if(isDisplayed) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val constraints = if (minWidth < 600.dp) {
                myDecoupledConstraints(0.3f)
            } else {
                myDecoupledConstraints(0.5f)
            }
            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
                constraintSet = constraints
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.layoutId("progressBar"),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Suppress("Deprecation")
private fun myDecoupledConstraints(verticalBias: Float): ConstraintSet {
    return ConstraintSet {
        val guideline = createGuidelineFromTop(verticalBias)
        val progressBar = createRefFor("progressBar")
        constrain(progressBar) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}