package com.gonzaloracergalan.portfolio.data.db.converter

import androidx.room.TypeConverter
import com.gonzaloracergalan.portfolio.data.db.calculated.ActiveResumeSection
import kotlinx.serialization.json.Json

class RoomConverter {
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toStringList(data: String?): List<String>? {
        return data?.let { Json.decodeFromString<List<String>>(it) }
    }

    @TypeConverter
    fun fromActiveResumeSectionsList(value: String?): List<ActiveResumeSection> {
        return value
            ?.split(",")
            ?.filter { it.isNotEmpty() }
            ?.map { ActiveResumeSection.valueOf(it) }
            ?: emptyList()
    }

    @TypeConverter
    fun activeResumeSectionsListToString(list: List<ActiveResumeSection>?): String {
        return list?.joinToString(",") ?: ""
    }
}