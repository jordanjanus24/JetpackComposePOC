package com.app.compose.presentation.components

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.app.compose.presentation.ui.recipe_list.FoodCategory
import com.app.compose.presentation.ui.recipe_list.getAllFoodCategories

@Composable
fun SearchToolbar(
    query: String,
    onQueryChanged: (String) -> Unit,
    selectedCategory: FoodCategory?,
    categoryScrollPosition: Float,
    onSelectedCategoryChanged: (String, Float) -> Unit,
    onExecuteSearch: () -> Unit)
{
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        elevation = 8.dp
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()){
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(5.dp)
                        ,
                        value = query,
                        onValueChange = onQueryChanged,
                        label = {
                            Text(text = "Search")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),
                        leadingIcon = {
                            Icon(Icons.Filled.Search)
                        },
                        onImeActionPerformed = { action, softKeyboardController ->
                            if (action == ImeAction.Done) {
                               onExecuteSearch()
                               softKeyboardController?.hideSoftwareKeyboard()
                            }
                        },
                        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                        backgroundColor = MaterialTheme.colors.surface
                    )
                }
            }
            val scrollState = rememberScrollState()
            ScrollableRow (
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                scrollState = scrollState
            ) {
                scrollState.scrollTo(categoryScrollPosition)
                for(category in getAllFoodCategories()) {
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory === category,
                        onSelectedCategoryChanged = { onSelectedCategoryChanged(it,scrollState.value) },
                        onExecuteSearch = onExecuteSearch
                    )
                }
            }
        }
    }
}