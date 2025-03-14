package com.insomniacapps.theoldlist.data

import androidx.compose.runtime.Immutable


interface TaskUiModelAction {
    fun onChecked()

    fun onStarClicked(isStarred: Boolean)

    fun onLongClicked()
}

@Immutable
class TaskUiData(val title: String,
                 val dueDate: String? = null,
                 val isStarred: Boolean,
                 val taskUiModelAction: TaskUiModelAction)