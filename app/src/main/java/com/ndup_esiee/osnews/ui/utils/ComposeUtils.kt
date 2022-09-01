package com.ndup_esiee.osnews.ui.utils

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun Modifier.onSimpleZoom(key1: Any? = Unit, action: (zoom: Float) -> Unit) = pointerInput(key1) {
    detectTransformGestures { _, _, zoom, _ -> action(zoom) }
}