package com.insomniacapps.theoldlist.settings

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/** A utils for shared preferences. */
class PreferenceUtils @Inject constructor(activity: Activity) {

    private val sharedPreferences: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)

    val currentlySetWallpaperId: Int
        get() =
            sharedPreferences.getInt(
                CURRENTLY_SET_WALLPAPER_KEY,
                WallpaperData.defaultWallpaperId
            )

    val currentlySetWallpaper: Int
        get() =
            WallpaperData.getWallPaperDrawableResId(
                sharedPreferences.getInt(
                    CURRENTLY_SET_WALLPAPER_KEY,
                    WallpaperData.defaultWallpaperId
                )
            )


    fun setWallpaper(wallpaperId: Int) {
        with(sharedPreferences.edit()) {
            putInt(CURRENTLY_SET_WALLPAPER_KEY, wallpaperId)
            apply()
        }
    }

    companion object {
        private val CURRENTLY_SET_WALLPAPER_KEY = "wallpaper_key"
    }
}