package com.insomniacapps.theoldlist.viewmodel

import androidx.lifecycle.ViewModel
import com.insomniacapps.theoldlist.settings.PreferenceUtils
import com.insomniacapps.theoldlist.taskdatasource.TasksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(preferenceUtils: PreferenceUtils, tasksRepo: TasksRepo): ViewModel() {

    private val _wallpaperState: MutableStateFlow<Int> = preferenceUtils.currentlySetWallpaper
    val wallpaperResState: StateFlow<Int> = _wallpaperState

}