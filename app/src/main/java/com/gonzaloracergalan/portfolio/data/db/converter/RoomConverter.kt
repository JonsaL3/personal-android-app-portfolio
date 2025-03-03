package com.gonzaloracergalan.portfolio.data.db.converter

import androidx.room.TypeConverter
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
}