package com.app.compose.utils

import android.os.Build
import android.text.Html


@Suppress("DEPRECATION")
fun fromHtml(html: String): String {
   return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY).toString()
    else Html.fromHtml(html).toString()
}