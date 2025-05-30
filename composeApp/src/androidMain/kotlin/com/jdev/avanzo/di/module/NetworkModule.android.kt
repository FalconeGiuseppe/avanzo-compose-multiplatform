package com.jdev.avanzo.di.module

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual val engine: HttpClientEngine
    get() = Android.create()