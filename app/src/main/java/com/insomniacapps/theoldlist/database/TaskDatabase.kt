package com.insomniacapps.theoldlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.insomniacapps.theoldlist.taskdatasource.Task
import com.insomniacapps.theoldlist.taskdatasource.TasksDao

@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        const val DATABASE_NAME = "the-old-list-database"
        internal fun getInstance(context: Context): TaskDatabase {
            return Room.databaseBuilder(context, TaskDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    }
                )
                .build()
        }
    }
}