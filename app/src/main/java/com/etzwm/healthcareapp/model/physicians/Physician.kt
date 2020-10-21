package com.etzwm.healthcareapp.model.physicians

data class Physician(
    val degree: String,
    val id: Int,
    val name: String,
    val speciality: Speciality
)