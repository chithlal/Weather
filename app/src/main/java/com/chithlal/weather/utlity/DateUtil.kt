package com.chithlal.weather.utlity

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object{
        fun getDayFromDate(date: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val date: Date = dateFormat.parse(date)
            val dayFormat = SimpleDateFormat("EEEE")
            return dayFormat.format(date)
        }
    }
}