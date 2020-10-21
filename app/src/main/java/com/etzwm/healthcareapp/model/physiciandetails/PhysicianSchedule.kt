package com.etzwm.healthcareapp.model.physiciandetails

data class PhysicianSchedule(
    val date_time: String,
    val hospital: Hospital,
    val id: Int,
    val physician: Physician
)