package com.insomniacapps.theoldlist.taskdatasource

import javax.inject.Inject

class TasksRepo @Inject constructor(private val taskDao: TasksDao) {
}