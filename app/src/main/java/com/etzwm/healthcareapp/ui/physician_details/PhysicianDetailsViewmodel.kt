package com.etzwm.healthcareapp.ui.physician_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.physiciandetails.PhysicianDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhysicianDetailsViewmodel: ViewModel() {

    private var physicianScheduleList: MutableLiveData<PhysicianDetails> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getPhysicianDetails(): LiveData<PhysicianDetails> = physicianScheduleList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadPhysicianDetails(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getPhysicianDetails(id)
        apiCall.enqueue(object: Callback<PhysicianDetails> {
            override fun onFailure(call: Call<PhysicianDetails>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<PhysicianDetails>, response: Response<PhysicianDetails>) {
                loading.value = false
                physicianScheduleList.value = response.body()
            }

        })
    }
}