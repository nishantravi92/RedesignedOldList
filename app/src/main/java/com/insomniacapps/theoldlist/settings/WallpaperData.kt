package com.insomniacapps.theoldlist.settings

import com.insomniacapps.theoldlist.R

object WallpaperData {
    private val idToResMap =
        mapOf(
            Pair(1, R.drawable.mountain),
            Pair(2, R.drawable.waterfall),
            Pair(4, R.drawable.milky_way)
        )

    val wallpaperIdAndResList: List<Pair<Int, Int>> get() = idToResMap.toList()

    val defaultWallpaperId get() = 3

    fun getWallPaperDrawableResId(wallpaperId: Int): Int = idToResMap[wallpaperId]!!
}