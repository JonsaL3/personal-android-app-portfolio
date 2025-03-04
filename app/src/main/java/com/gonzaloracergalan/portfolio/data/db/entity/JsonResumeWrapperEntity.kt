package com.gonzaloracergalan.portfolio.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gonzaloracergalan.portfolio.data.dt.dto.JsonResumeWrapperDTO

@Entity(tableName = "resumes")
data class JsonResumeWrapperEntity(
    @PrimaryKey(autoGenerate = true)
    val resumeId: Long = 0
) {
    fun toDTO(
        // TODO
    ): JsonResumeWrapperDTO {
        return JsonResumeWrapperDTO()
    }
}