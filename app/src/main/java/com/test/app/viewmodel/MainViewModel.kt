package com.test.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.app.model.SectionWithAlerts
import com.test.app.model.data.Sections
import com.test.app.repository.AlertRepositoryImpl
import com.test.app.room.Alert
import com.test.app.room.AlertDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Duration
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val alertRepository: AlertRepositoryImpl
) : ViewModel() {

    //private val alertRepository = AlertRepository(database.alertDao())
    private val sectionWithAlerts: MutableLiveData<List<SectionWithAlerts>> = MutableLiveData()
    private val alertsCount: MutableLiveData<Int> = MutableLiveData(0)

    private fun insert(alert: Alert) {
        viewModelScope.launch(Dispatchers.IO) {
            alertRepository.insert(alert)
        }
    }

    private fun clearAllAlerts () {
        viewModelScope.launch(Dispatchers.IO) {
            alertRepository.clearAllAlerts()
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetch()
        }
    }

    fun scan (duration: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(duration)
            clearAllAlerts()
            val sections = Sections.getAllSections
            for (i in 0..(3..<10).random()) {
                val section = sections[(sections.indices).random()]
                val alert = Alert(0, section.sectionId, "")
                insert(alert)
            }
            fetch()
        }
    }

    private suspend fun fetch () {
        withContext(Dispatchers.IO) {
            val sections = Sections.getAllSections
            var count = 0
            val sectionWithAlertsList = mutableListOf<SectionWithAlerts>()
            for (section in sections) {
                val alerts = alertRepository.getAlertsBySection(section.sectionId)
                count += alerts.size
                val sectionWithAlerts = SectionWithAlerts(section, alerts)
                sectionWithAlertsList.add(sectionWithAlerts)
            }
            alertsCount.postValue(count)
            sectionWithAlerts.postValue(sectionWithAlertsList)
        }
    }

    fun getAlertsCount () : LiveData<Int> {
        return alertsCount
    }

    fun getAllSectionWithAlerts () : LiveData<List<SectionWithAlerts>> {
        return sectionWithAlerts
    }

}