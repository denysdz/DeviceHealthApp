package com.test.app.di

import com.test.app.room.AlertDao
import com.test.app.room.AlertDatabase

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideAlertDao(alertDatabase: AlertDatabase): AlertDao {
        return alertDatabase.alertDao()
    }

    @Provides
    @Singleton
    fun provideAlertDatabase(@ApplicationContext context: Context): AlertDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AlertDatabase::class.java,
            "alert_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}
