package com.jdev.avanzo.presentation.navigation

import avanzo.composeapp.generated.resources.Res
import avanzo.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.StringResource

enum class NavigationScreen(val title: StringResource) {
    Start(title = Res.string.app_name)
}
