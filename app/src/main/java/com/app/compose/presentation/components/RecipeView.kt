package com.app.compose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.app.compose.domain.model.Recipe
import com.app.compose.utils.DEFAULT_RECIPE_IMAGE
import com.app.compose.utils.loadPicture

const val IMAGE_HEIGHT = 260
@Composable
fun RecipeView(recipe: Recipe) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            recipe.featuredImage?.let { url ->
                val image = loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        modifier = Modifier.fillMaxWidth()
                            .requiredHeight(IMAGE_HEIGHT.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Recipe"
                    )
                }
            }
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                recipe.title?.let { title ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier.fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h3
                        )
                        val rank = recipe.rating.toString()
                        Text(
                            text = rank,
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h5
                        )
                    }
                    recipe.publisher?.let { publisher ->
                        val updated = recipe.dateUpdated
                        Text(
                            text = if (updated !== null) "Updated $updated by $publisher" else "By $publisher",
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                            style = MaterialTheme.typography.caption
                        )
                    }
                    for (ingredient in recipe.ingredients) {
                        Text(
                            text = ingredient,
                            modifier = Modifier.fillMaxWidth()
                                .padding(bottom = 8.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}