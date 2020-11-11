package com.fturek.todolist.ui.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        fun getDateInFormat(date: Date?): String {
            if (date == null) {
                return ""
            }
            val df: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault())
            return df.format(date)
        }

    }
}