package com.insomniacapps.theoldlist.data

import kotlinx.coroutines.flow.MutableStateFlow

/** Data required to render the list of avalailble wallpapers for selection. */
class WallpaperUiData(val title: String, val wallpapers: List<Wallpaper>)

/** Renders a single wallpaper. */
class Wallpaper(
    val wallpaperResId: Int,
)