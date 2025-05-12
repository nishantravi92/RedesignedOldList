package com.insomniacapps.theoldlist.data

import androidx.compose.runtime.Immutable


interface TaskUiModelAction {
    fun onChecked(taskId: String)

    fun onStarClicked(isStarred: Boolean, taskId: String)

    fun onLongClicked(taskId: String)
}

@Immutable
class TaskUiData(
    val id: String,
    val title: String,
    val dueDate: String? = null,
    val isStarred: Boolean,
    val taskUiModelAction: TaskUiModelAction
)