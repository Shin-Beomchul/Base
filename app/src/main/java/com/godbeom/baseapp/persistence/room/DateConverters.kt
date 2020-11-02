package com.godbeom.baseapp.persistence.room

import androidx.room.TypeConverter
import com.godbeom.baseapp.model.Image
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromImage(image: Image?): String? {
        return image?.hostFileNo
    }

    @TypeConverter
    fun toImage(hostFileNo: String?): Image? {
        return hostFileNo?.let { Image(it) }
    }
}