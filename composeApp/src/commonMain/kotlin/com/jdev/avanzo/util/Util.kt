package com.jdev.avanzo.util

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.toByteArray(): ByteArray =
    Base64.decode(this.encodeToByteArray(), 0, this.encodeToByteArray().size)