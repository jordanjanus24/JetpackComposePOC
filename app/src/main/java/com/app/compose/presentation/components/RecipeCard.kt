package com.app.compose.presentation.components

import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.app.compose.R
import com.app.compose.domain.model.Recipe
import com.app.compose.utils.DEFAULT_RECIPE_IMAGE
import com.app.compose.utils.fromHtml
import com.app.compose.utils.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun RecipeCard(
    recipe : Recipe,
    onClick : () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable(
                onClick = onClick),
        elevation = 8.dp
    ) {
        Column {
            recipe.featuredImage?.let { url ->
                val image = loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(225.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Recipe Featured Image"
                    )
                }

            }
            recipe.title?.let { title ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top=12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = fromHtml(title),
                        modifier = Modifier.fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h4
                    )
                    Text(
                        text = recipe.rating.toString(),
                        modifier = Modifier.fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}