package com.test.app.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.test.app.model.Section

@Entity(tableName = "alerts")
data class Alert(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val sectionId: Int, // Додайте це поле
    val message: String
)