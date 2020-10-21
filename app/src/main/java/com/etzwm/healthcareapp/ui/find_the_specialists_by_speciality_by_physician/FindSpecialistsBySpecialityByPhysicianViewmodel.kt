package com.etzwm.healthcareapp.ui.find_the_specialists_by_speciality_by_physician

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.physicians.PhysicianResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindSpecialistsBySpecialityByPhysicianViewmodel: ViewModel() {

    private var physicianBySpeciality: MutableLiveData<PhysicianResult> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getPhysicianBySpeciality(): LiveData<PhysicianResult> = physicianBySpeciality
    fun getLoading(): LiveData<Boolean> = loading

    fun loadPhysicianBySpeciality(sid: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getPhysicianBySpeciality(sid)

        apiCall.enqueue(object: Callback<PhysicianResult> {
            override fun onFailure(call: Call<PhysicianResult>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<PhysicianResult>, response: Response<PhysicianResult>) {
                loading.value = false
                physicianBySpeciality.value = response.body()
            }

        })
    }
}