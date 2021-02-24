package com.app.compose.network.response

import com.app.compose.network.model.RecipeDTO
import com.google.gson.annotations.SerializedName


class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDTO>,
)