package com.app.compose.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose.domain.model.Recipe
import com.app.compose.presentation.ui.recipe_list.RecipeListEvent.*
import com.app.compose.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PAGE_SIZE = 30

const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY = "recipe.state.query.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query.selected_category"

class RecipeListViewModel
@ViewModelInject
constructor(
    private val repository: RecipeRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Float = 0f
    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)
    private var recipeListScrollPosition = 0
    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { lp ->
           setListScrollPosition(lp)
        }
        savedStateHandle.get<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)?.let { c ->
            setSelectedCategory(c)
        }
        if(recipeListScrollPosition != 0) {
            onTriggerEvent(RestoreStateEvent)
        } else {
            onTriggerEvent(NewSearchEvent)
        }
    }
    fun onTriggerEvent(event: RecipeListEvent) {
        viewModelScope.launch {
            try {
                when(event) {
                    is NewSearchEvent -> {
                        newSearch()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    is RestoreStateEvent -> {
                        restoreState()
                    }
                }
            } catch(e: Exception) { }
        }
    }
    private suspend fun restoreState() {
        loading.value = true
        val results: MutableList<Recipe> = mutableListOf()
        for(p in 1..page.value) {
            val result = repository.search(page = p, query = query.value )
            results.addAll(result)
            if(p == page.value){ // done
                recipes.value = results
                loading.value = false
            }
        }
    }

    private suspend fun newSearch(){
        loading.value = true
        resetSearchState()
        delay(1000)
        val result = repository.search(
            page = 1,
            query = query.value
        )
        recipes.value = result
        loading.value = false
    }
    private suspend fun nextPage() {
        if((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE) ) {
            loading.value = true
            incrementPageNumber()
            delay(1000)
            if(page.value > 1){
                val result = repository.search(page = page.value, query = query.value )
                appendRecipes(result)
            }
            loading.value = false
        }
    }
    private fun appendRecipes(recipe: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipe)
        this.recipes.value = current
    }
    private fun incrementPageNumber() {
        setPage(page.value + 1)
    }
    fun onChangeRecipeScrollPosition(position: Int) {
        setListScrollPosition(position)
    }
    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if(selectedCategory.value?.value != query.value) {
            setSelectedCategory(null)
        }
    }

    fun onQueryChanged(query: String){
        setQuery(query)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        setSelectedCategory(newCategory)
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float){
        categoryScrollPosition = position
    }

    private fun setListScrollPosition(position: Int) {
        recipeListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE,page)
    }
    private fun setSelectedCategory(category: FoodCategory?) {
        selectedCategory.value = category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }
    private fun setQuery(query: String) {
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }
}