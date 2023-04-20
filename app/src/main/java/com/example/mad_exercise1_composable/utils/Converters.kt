package com.example.mad_exercise1_composable.utils

import androidx.room.TypeConverter
import com.example.mad_exercise1_composable.models.Genre

private const val SEPARATOR = ","

class Converters {

    @TypeConverter
    fun fromGenreList(genre: List<Genre>): String {
        return genre.joinToString(separator = SEPARATOR) { it.name }
    }

    @TypeConverter
    fun toGenreList(genre: String): List<Genre> {
        return genre.split(SEPARATOR).map { Genre.valueOf(it)}
    }

    @TypeConverter
    fun fromImageList(images: List<String>): String {
        return images.joinToString(separator = SEPARATOR) {it}
    }

    @TypeConverter
    fun toImageList(images: String): List<String> {
        return images.split(SEPARATOR)
    }
}