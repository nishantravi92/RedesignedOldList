package com.insomniacapps.theoldlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.insomniacapps.theoldlist.data.TaskUiData
import com.insomniacapps.theoldlist.data.TaskUiModelAction
import com.insomniacapps.theoldlist.settings.PreferenceUtils
import com.insomniacapps.theoldlist.taskdatasource.Task
import com.insomniacapps.theoldlist.taskdatasource.TasksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    preferenceUtils: PreferenceUtils,
    private val tasksRepo: TasksRepo,
) :
    ViewModel() {

    private val _wallpaperState: MutableStateFlow<Int> = preferenceUtils.currentlySetWallpaper
    val wallpaperResState: StateFlow<Int> = _wallpaperState

    private val taskModelUiAction: TaskUiModelAction = object : TaskUiModelAction {
        override fun onChecked(taskId: String) {
            tasksRepo.completeTask(taskId, viewModelScope)
        }

        override fun onStarClicked(isStarred: Boolean, taskId: String) {
            tasksRepo.starTask(taskId, isStarred, viewModelScope)
        }

        override fun onLongClicked(taskId: String) {
        }
    }
    val tasks: Flow<PagingData<TaskUiData>>
        get() = tasksRepo.getPagedTasks(viewModelScope)
            .map { pagingData: PagingData<Task> ->
                pagingData.map { task ->
                    TaskUiData(
                        id = task.id,
                        title = task.title,
                        dueDate = task.dueDate.toString(),
                        isStarred = task.starred,
                        taskUiModelAction =taskModelUiAction)
                }
            }

    companion object {
        val TEST_DATA = TaskUiData(
            id = "abc",
            title = "My task",
            dueDate = "10/23/2025",
            isStarred = true,
            taskUiModelAction = object : TaskUiModelAction {
                override fun onChecked(taskId: String) {
                    TODO("Not yet implemented")
                }

                override fun onStarClicked(isStarred: Boolean, taskId: String) {
                    TODO("Not yet implemented")
                }

                override fun onLongClicked(taskId: String) {
                    TODO("Not yet implemented")
                }
            })
    }
}