package com.veyo.ebook.utility

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Veyo Nuon on 9/25/2019.
 */
object DateTimeUtil {
    const val FORMAT_DATE_1 = "dd-MM-yyyy"
    const val FORMAT_DATE_SERVER = "yyyy-MM-dd'T'HH:mm:ss'+'00:00"

    fun formatDate(date: Date?, formatStr: String): String? {
        date?.let {
            return SimpleDateFormat(formatStr, Locale.getDefault()).format(date)
        }
        return null
    }

    fun covertTimeFromServerToLocalDate(time: String?, format: String): Date? {
        if (time.isNullOrEmpty()) return Date()
        return SimpleDateFormat(format, Locale.getDefault()).parse(time)
    }
}