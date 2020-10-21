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
import retrofit2.http.*

interface ApiInterface {

    @GET("hospital")
    fun getHospital(
    ): Call<HospitalResult>

    @GET("speciality")
    fun getSpeciality(
    ): Call<SpecialityResult>

    @GET("physician")
    fun getPhysician(
    ): Call<PhysicianResult>

    @GET("package")
    fun getMedicalCheckupPackages(
        @Query("hospital") hospitalId: Int
    ): Call<PackagesResult>

    @GET("hospital/{hospital_id}")
    fun getSpecialityByHospital(
        @Path("hospital_id") hospitalId: Int
    ): Call<HospitalDetails>

    @GET("physician/{physician_id}")
    fun getPhysicianDetails(
        @Path("physician_id") physicianId: Int
    ): Call<PhysicianDetails>

    @GET("physician")
    fun getPhysicianBySpeciality(
        @Query("speciality_id") specialityId: Int
    ): Call<PhysicianResult>

    @GET("physician")
    fun getPhysicianBySpecialityByHospital(
        @Query("speciality_id") specialityId: Int,
        @Query("hospital_id") hospitalId: Int
    ): Call<PhysicianResult>

    @GET("physician")
    fun getPhysicianSearchName(
        @Query("keyword") physicianName: String
    ): Call<PhysicianResult>

    @FormUrlEncoded
    @POST("recommend")
    fun writeRecommendSuggest(
        @Field("person_name") personName: String,
        @Field("person_email") personEmail: String,
        @Field("person_mobile_no") personMobileNo: String,
        @Field("subject") subject: String,
        @Field("rec_date") date: String,
        @Field("hospital") hospitalId: Int
    ): Call<DefaultResponse>

    @GET("recommend")
    fun getRecommendSuggest(
    ): Call<RecommendResult>
}