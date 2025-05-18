package com.insomniacapps.theoldlist.taskdatasource

import androidx.paging.PagingSource
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import java.util.Calendar
import java.util.Date
import java.util.UUID
import kotlin.uuid.Uuid

@Entity
data class Task(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "due_date") val dueDate: Date? = null,
    @ColumnInfo(name = "starred") val starred: Boolean = false,
) {
    companion object {
        fun generateUuid():String = UUID.randomUUID().toString()
    }
}

@Dao
interface TasksDao {

    @Query("select * from task order by id desc")
    fun getAllTasksPagingSource(): PagingSource<Int, Task>

    @Query("select * from task where due_date is not null and (:date >= due_date or cast(:date as text) == cast(due_date as text))")
    fun getTasksByDate(date: Date): PagingSource<Int, Task>

    @Query("select count(*) from task")
    fun getTasksCount(): Int

    @Query("select count(*) from task where starred")
    fun getTasksStarredCount(): Int

    @Query("select * from task where starred order by id desc ")
    fun getAllStarredTasks(): PagingSource<Int, Task>

    @Query("select count(*) from task where due_date is not null and :date >= due_date")
    fun getTasksCountByDate(date: Date = Date(Calendar.getInstance().time.time)): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query("select * from task where id = :id")
    suspend fun getTask(id: String): Task

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: String)

    @Query("UPDATE task SET starred=:isStarred WHERE id=:taskId")
    suspend fun starTask(taskId: String, isStarred: Boolean)
}