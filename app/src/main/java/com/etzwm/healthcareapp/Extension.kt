package com.etzwm.healthcareapp

import java.text.SimpleDateFormat
import java.util.*

fun toSimpleString(rec_date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    val date = inputFormat.parse(rec_date)
    return outputFormat.format(date)
}