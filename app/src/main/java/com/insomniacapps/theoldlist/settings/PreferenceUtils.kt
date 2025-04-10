package com.insomniacapps.theoldlist.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/** A utils for shared preferences. */
@Singleton
class PreferenceUtils @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERNCE_FILE, 0)
    val currentlySetWallpaper = MutableStateFlow(ResourcesCompat.ID_NULL)

    init {
        MainScope().launch {
            currentlySetWallpaper.value = sharedPreferences.getInt(
                CURRENTLY_SET_WALLPAPER_KEY,
                WallpaperData.defaultWallpaperResId
            )
        }
    }

    fun updateCurrentWallPaper(@DrawableRes id: Int) {
        MainScope().launch {
            currentlySetWallpaper.value = id
            // TODO Update the actual store as well.
        }
    }

    companion object {
        private val CURRENTLY_SET_WALLPAPER_KEY = "wallpaper_key"
        private val SHARED_PREFERNCE_FILE = "shared_preference"
    }
}