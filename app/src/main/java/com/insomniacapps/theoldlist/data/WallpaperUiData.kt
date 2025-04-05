package com.insomniacapps.theoldlist.data

import kotlinx.coroutines.flow.MutableStateFlow

/** Data required to render the list of avalailble wallpapers for selection. */
class WallpaperUiData(val title: String, currentlySetWallpaperResId: MutableStateFlow<Int>, val wallpapers: List<Wallpaper>)

/** Renders a single wallpaper. */
class Wallpaper(
    val wallpaperId: Int,
    val wallpaperResId: Int,
    val isCurrentlySelected: Boolean,
    val wallpaperSettingsUiAction: WallpaperSettingsUiAction
)

/** Action handler for a wallpaper */
interface WallpaperSettingsUiAction {
    /** Callback for when a wallpaper has changed. */
    fun onWallpaperChanged(wallpaperId: Int)
}