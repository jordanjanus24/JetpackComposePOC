package com.app.compose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.app.compose.presentation.ui.recipe_list.FoodCategory


@Suppress("Deprecation")
@Composable
fun SearchToolbar(
    query: String,
    onQueryChanged: (String) -> Unit,
    categories: List<FoodCategory>,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    onToggleTheme: () -> Unit
) {
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth().padding(top= 30.dp)){
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp)
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
                        Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                        }
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    backgroundColor = MaterialTheme.colors.surface
                )
                ConstraintLayout(modifier = Modifier.align(Alignment.CenterVertically)) {
                    val menu = createRef()
                    IconButton(onClick = onToggleTheme,
                        modifier = Modifier.constrainAs(menu) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Toggle Dark/Light Theme")
                    }
                }
            }
            val scrollState = rememberLazyListState()
            LazyRow (
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                state = scrollState
            ) {
                items(categories) { category ->
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory === category,
                        onSelectedCategoryChanged = { onSelectedCategoryChanged(it) },
                        onExecuteSearch = onExecuteSearch
                    )
                }
            }
        }
    }
}