package com.insomniacapps.theoldlist.taskdatasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksRepo @Inject constructor(private val tasksDao: TasksDao) {

    fun getPagedTasks(scope: CoroutineScope): Flow<PagingData<Task>> {
        return Pager(config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = {tasksDao.getAllTasksPagingSource()})
            .flow.cachedIn(scope).flowOn(Dispatchers.IO)
    }


    fun completeTask(taskId: String, scope: CoroutineScope) {
        scope.launch {
            tasksDao.deleteTask(taskId)
        }
    }

    fun starTask(taskId: String, isStarred: Boolean, scope: CoroutineScope) {
        scope.launch {
            tasksDao.starTask(taskId, isStarred)
        }
    }

    fun addTask(task: Task, scope: CoroutineScope) {
        scope.launch {
            tasksDao.addTask(task)
        }
    }

    suspend fun getTask(id: String): Task  = tasksDao.getTask(id)

    companion object {
        const val PAGE_SIZE = 10
    }
}