package com.etzwm.healthcareapp.ui.hospital_contacts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.hospitals.HospitalResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalContactsViewmodel: ViewModel() {

    private var hospitalList: MutableLiveData<HospitalResult> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getHospital(): LiveData<HospitalResult> = hospitalList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadHospital() {
        var apiClient = ApiClient()
        var apiCall = apiClient.getHospital()
        apiCall.enqueue(object: Callback<HospitalResult> {

            override fun onFailure(call: Call<HospitalResult>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<HospitalResult>, response: Response<HospitalResult>) {
                loading.value = false
                hospitalList.value = response.body()
            }
        })
    }
}