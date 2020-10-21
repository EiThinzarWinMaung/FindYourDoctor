package com.etzwm.healthcareapp.model.schedule

data class Physician(
    val degree: String,
    val id: Int,
    val name: String,
    val speciality: SpecialityX
)