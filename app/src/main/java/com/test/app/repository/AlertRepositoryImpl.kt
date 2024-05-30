package com.test.app.repository

import androidx.lifecycle.LiveData
import com.test.app.room.Alert
import com.test.app.room.AlertDao
import javax.inject.Inject

class AlertRepositoryImpl @Inject constructor(
    private val alertDao : AlertDao
) {

    suspend fun insert(alert: Alert) {
        alertDao.insert(alert)
    }

    fun getAlertsBySection(sectionId: Int): List<Alert> {
        return alertDao.getAlertsBySection(sectionId)
    }

    suspend fun clearAllAlerts() {
        alertDao.deleteAllAlerts()
    }
}