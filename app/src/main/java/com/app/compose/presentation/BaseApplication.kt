package com.app.compose.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    val isDark = mutableStateOf(false)
    fun toggleLightTheme() {
        isDark.value = !isDark.value
        AppCompatDelegate.setDefaultNightMode(if(isDark.value) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

}