package com.jdev.avanzo.util

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
actual fun ByteArray.toImageBitmapFromBase64(): ImageBitmap? =
     BitmapFactory.decodeByteArray(this, 0, this.size)?.asImageBitmap()
