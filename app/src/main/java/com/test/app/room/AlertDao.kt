package com.test.app.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlertDao {
    @Insert
    fun insert(alert: Alert)

    @Query("SELECT * FROM alerts WHERE sectionId = :sectionId")
    fun getAlertsBySection(sectionId: Int): List<Alert>

    @Query("SELECT * FROM alerts")
    fun getAllAlerts(): List<Alert>

    @Query("DELETE FROM alerts")
    fun deleteAllAlerts()

}