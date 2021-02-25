package com.app.compose.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.app.compose.presentation.BaseApplication
import com.app.compose.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(
                    darkTheme = application.isDark.value
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Recipe Fragment",
                            style = TextStyle(
                                fontSize = TextUnit.Sp(21)
                            )
                        )
                    }
                }
            }
        }
    }
}