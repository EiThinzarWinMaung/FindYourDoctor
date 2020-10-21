package com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital_by_speciality

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.hospitaldetails.HospitalDetails
import com.etzwm.healthcareapp.model.hospitals.HospitalResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindSpecialistsByHospitalBySpecialityViewmodel: ViewModel() {

    private var specialityByHospitalList: MutableLiveData<HospitalDetails> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getSpecialityByHospital(): LiveData<HospitalDetails> = specialityByHospitalList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadSpecialityByHospital(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getSpecialityByHospital(id)
        apiCall.enqueue(object: Callback<HospitalDetails> {
            override fun onFailure(call: Call<HospitalDetails>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<HospitalDetails>, response: Response<HospitalDetails>) {
                loading.value = false
                specialityByHospitalList.value = response.body()
            }

        })
    }
}