package com.app.compose.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.app.compose.R
import com.app.compose.presentation.ui.recipe_list.FoodCategory
import com.app.compose.presentation.util.ConnectionLiveData


@ExperimentalComposeUiApi
@Suppress("Deprecation")
@ExperimentalAnimationApi
@Composable
fun SearchToolbar(
    query: String,
    onQueryChanged: (String) -> Unit,
    categories: List<FoodCategory>,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    onToggleTheme: () -> Unit,
    connectionLiveData: ConnectionLiveData
) {
    val isNetworkAvailable = connectionLiveData.observeAsState(false).value
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp
    ) {
        Column {
            AnimatedVisibility(visible = !isNetworkAvailable) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.error)
                    .padding(top = 40.dp, bottom = 8.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onError),
                    text = stringResource(id = R.string.no_internet_connection))
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(top = if (isNetworkAvailable) 30.dp else 0.dp)){
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
                            keyboardController?.hideSoftwareKeyboard()
                        }
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
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
                modifier = Modifier
                    .fillMaxWidth()
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