package com.insomniacapps.theoldlist.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.clipRoundedShape(size: Dp = 4.dp): Modifier {
    return this.clip(
        RoundedCornerShape(size)
    )
}