package com.etzwm.healthcareapp.model.recommend

data class Recommend(
    val hospital: Hospital,
    val person_email: String,
    val person_mobile_no: String,
    val person_name: String,
    val rec_date: String,
    val recommend_id: Int,
    val subject: String
)