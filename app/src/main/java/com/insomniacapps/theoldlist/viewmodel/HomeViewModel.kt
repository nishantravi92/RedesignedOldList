package com.insomniacapps.theoldlist.viewmodel

import androidx.lifecycle.ViewModel
import com.insomniacapps.theoldlist.settings.PreferenceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(preferenceUtils: PreferenceUtils): ViewModel() {

    private val _wallpaperState: MutableStateFlow<Int> = preferenceUtils.currentlySetWallpaper
    val wallpaperResState: StateFlow<Int> = _wallpaperState

}