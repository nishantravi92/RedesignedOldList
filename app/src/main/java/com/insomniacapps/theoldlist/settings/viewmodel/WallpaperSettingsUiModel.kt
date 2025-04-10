package com.insomniacapps.theoldlist.settings.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insomniacapps.theoldlist.data.Wallpaper
import com.insomniacapps.theoldlist.data.WallpaperUiData
import com.insomniacapps.theoldlist.settings.PreferenceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.insomniacapps.theoldlist.R
import kotlinx.coroutines.launch

/** A ui model for the wallpaper setttings screen */
@HiltViewModel
class WallpaperSettingsViewModel @Inject constructor(private val preferenceUtils: PreferenceUtils) :
    ViewModel() {

    val currentlySetWallpaperResId = preferenceUtils.currentlySetWallpaper

    private val _wallpaperUiData =
        MutableStateFlow(WallpaperUiData(title = "Wallpaper Settings", WALLPAPERS))

    val wallpaperUiData: StateFlow<WallpaperUiData> = _wallpaperUiData

    fun updateWallPaper(@DrawableRes wallpaperResId: Int) {
        viewModelScope.launch {
            preferenceUtils.updateCurrentWallPaper(wallpaperResId)
        }
    }

    private companion object {
        val WALLPAPERS = listOf(
            Wallpaper(wallpaperResId = R.drawable.mountain),
            Wallpaper(wallpaperResId = R.drawable.waterfall),
            Wallpaper(R.drawable.milky_way)
        )
    }
}