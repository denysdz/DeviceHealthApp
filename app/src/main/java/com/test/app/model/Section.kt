package com.test.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Section(
    @PrimaryKey val sectionId: Int,
    val resId:Int,
    val title:String,
    val description:String
)
