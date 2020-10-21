package com.etzwm.healthcareapp.model.schedule

data class Schedule(
    val date_time: String,
    val hospital: Hospital,
    val id: Int,
    val physician: Physician
)