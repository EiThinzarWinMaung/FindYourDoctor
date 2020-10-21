package com.etzwm.healthcareapp.ui.medical_checkup_packages

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.medicalcheckuppackages.PackagesResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicalCheckupPackagesViewmodel: ViewModel() {

    private var medicalCheckupPackagesList: MutableLiveData<PackagesResult> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getMedicalCheckupPackages(): LiveData<PackagesResult> = medicalCheckupPackagesList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadMedicalCheckupPackages(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getMedicalCheckupPackages(id)
        apiCall.enqueue(object: Callback<PackagesResult> {
            override fun onFailure(call: Call<PackagesResult>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<PackagesResult>, response: Response<PackagesResult>) {
                loading.value = false
                medicalCheckupPackagesList.value = response.body()
            }
        })
    }
}