package com.test.app.model

import com.test.app.room.Alert

data class SectionWithAlerts(
    val section: Section,
    val alerts: List<Alert>
)