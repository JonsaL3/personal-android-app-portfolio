package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resumes")
data class JsonResumeWrapperEntity(
    @PrimaryKey(autoGenerate = true)
    val resumeId: Long = 0
)