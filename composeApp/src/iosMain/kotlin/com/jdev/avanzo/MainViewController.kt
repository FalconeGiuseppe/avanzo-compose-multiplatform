package com.jdev.avanzo

import androidx.compose.ui.window.ComposeUIViewController
import com.jdev.avanzo.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { AvanzoApp() }