package com.jdev.avanzo.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import org.jetbrains.skia.Image

@OptIn(ExperimentalForeignApi::class)
actual fun String.toImageBitmapFromBase64(): ImageBitmap?  {
    return Image.makeFromEncoded(bytes = this.encodeToByteArray()).toComposeImageBitmap()
}
