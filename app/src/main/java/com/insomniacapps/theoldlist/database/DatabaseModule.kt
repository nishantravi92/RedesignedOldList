package com.insomniacapps.theoldlist.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = TaskDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideTasksDao(taskDatabase: TaskDatabase) = taskDatabase.tasksDao()
}