package com.example.project3.data

import androidx.room.TypeConverter
import java.time.Instant


class MyTypeConverters {
    @TypeConverter
    fun toInstant(millis: Long): Instant {
        return Instant.ofEpochMilli(millis)
    }

    @TypeConverter
    fun toMillis(instant: Instant): Long {
        return instant.toEpochMilli();
    }
}