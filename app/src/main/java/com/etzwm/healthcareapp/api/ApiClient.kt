package com.etzwm.healthcareapp.api

import com.etzwm.healthcareapp.model.hospitaldetails.HospitalDetails
import com.etzwm.healthcareapp.model.hospitals.HospitalResult
import com.etzwm.healthcareapp.model.medicalcheckuppackages.PackagesResult
import com.etzwm.healthcareapp.model.physiciandetails.PhysicianDetails
import com.etzwm.healthcareapp.model.physicians.PhysicianResult
import com.etzwm.healthcareapp.model.recommend.DefaultResponse
import com.etzwm.healthcareapp.model.recommend.RecommendResult
import com.etzwm.healthcareapp.model.specialities.SpecialityResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "http://hospitalguideapi.dsv.hoz.mybluehost.me/api/"

    companion object {
        val imgURL = "http://hospitalguideapi.dsv.hoz.mybluehost.me"
    }

    private val apiInterface: ApiInterface

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getHospital(
    ): Call<HospitalResult> {
        return apiInterface.getHospital()
    }

    fun getSpeciality(
    ): Call<SpecialityResult> {
        return apiInterface.getSpeciality()
    }

    fun getPhysician(
    ): Call<PhysicianResult> {
        return apiInterface.getPhysician()
    }

    fun getMedicalCheckupPackages(
        hospitalId: Int
    ): Call<PackagesResult> {
        return apiInterface.getMedicalCheckupPackages(hospitalId)
    }

    fun getSpecialityByHospital(
        hospitalId: Int
    ): Call<HospitalDetails> {
        return apiInterface.getSpecialityByHospital(hospitalId)
    }

    fun getPhysicianDetails(
        physicianId: Int
    ): Call<PhysicianDetails> {
        return apiInterface.getPhysicianDetails(physicianId)
    }

    fun getPhysicianBySpeciality(
        specialityId: Int
    ): Call<PhysicianResult> {
        return apiInterface.getPhysicianBySpeciality(specialityId)
    }

    fun getPhysicianBySpecialityByHospital(
        specialityId: Int,
        hospitalId: Int
    ): Call<PhysicianResult> {
        return apiInterface.getPhysicianBySpecialityByHospital(specialityId, hospitalId)
    }

    fun getPhysicianSearchName(
        physicianName: String
    ): Call<PhysicianResult> {
        return apiInterface.getPhysicianSearchName(physicianName)
    }

    fun writeRecommendSuggest(
        personName: String,
        personEmail: String,
        personMobileNo: String,
        subject: String,
        date: String,
        hospitalId: Int
    ): Call<DefaultResponse> {
        return apiInterface.writeRecommendSuggest(personName, personEmail, personMobileNo, subject, date, hospitalId)
    }

    fun getRecommendSuggest(
    ): Call<RecommendResult> {
        return apiInterface.getRecommendSuggest()
    }
}