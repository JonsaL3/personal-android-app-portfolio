package com.gonzaloracergalan.portfolio.data.db.converter

import androidx.room.TypeConverter
import com.gonzaloracergalan.portfolio.data.db.calculated.ActiveResumeSectionsCalculated
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
    fun fromActiveResumeSectionsList(value: String?): List<ActiveResumeSectionsCalculated.Section> {
        return value
            ?.split(",")
            ?.filter { it.isNotEmpty() }
            ?.map { ActiveResumeSectionsCalculated.Section.valueOf(it) }
            ?: emptyList()
    }

    @TypeConverter
    fun activeResumeSectionsListToString(list: List<ActiveResumeSectionsCalculated.Section>?): String {
        return list?.joinToString(",") ?: ""
    }
}