package com.etzwm.healthcareapp.ui.find_the_specialists_by_name

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.physicians.PhysicianResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindSpecialistsByNameViewmodel: ViewModel() {

    private var physicianList: MutableLiveData<PhysicianResult> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getPhysician(): LiveData<PhysicianResult> = physicianList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadPhysician() {
        var apiClient = ApiClient()
        val apiCall = apiClient.getPhysician()
        apiCall.enqueue(object: Callback<PhysicianResult> {
            override fun onFailure(call: Call<PhysicianResult>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<PhysicianResult>, response: Response<PhysicianResult>) {
                loading.value = false
                physicianList.value = response.body()
            }
        })
    }

    fun loadPhysicianSearchName(physicianName: String) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getPhysicianSearchName(physicianName)
        apiCall.enqueue(object: Callback<PhysicianResult> {
            override fun onFailure(call: Call<PhysicianResult>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<PhysicianResult>, response: Response<PhysicianResult>) {
                loading.value = false
                physicianList.value = response.body()
            }
        })
    }
}