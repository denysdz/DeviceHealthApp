package com.test.app.model.data

import com.test.app.R
import com.test.app.model.Section

object Sections {

    val getAllSections = listOf(
        Section(
            sectionId = 1,
            resId = R.drawable.ic_info,
            title = "Device info",
            description = "Show you all info about phone"
        ),
        Section(
            sectionId = 2,
            resId = R.drawable.ic_callibration_sensor,
            title = "Calibration of sensors",
            description = "Show you all info about"
        ),
        Section(
            sectionId = 3,
            resId = R.drawable.ic_app_monitoring,
            title = "App monitoring",
            description = "Show you all info about"
        ),
        Section(
            sectionId = 4,
            resId = R.drawable.ic_antivirus,
            title = "AntiVirus",
            description = "Show you all info about"
        ),
        Section(
            sectionId = 5,
            resId = R.drawable.ic_device_memory_info,
            title = "Device Memory Information",
            description = "Show you all info about"
        ),
        Section(
            sectionId = 6,
            resId = R.drawable.ic_file_manager,
            title = "File Manager",
            description = "Show you all info about"
        ),
        Section(
            sectionId = 7,
            resId = R.drawable.ic_battery_info,
            title = "Battery info",
            description = "Show you all info about"
        )
    )

}