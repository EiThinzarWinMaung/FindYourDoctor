package com.etzwm.healthcareapp.ui.find_the_specialists_by_speciality

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.specialities.SpecialityResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindSpecialistsBySpecialityViewmodel: ViewModel() {

    private var specialityList: MutableLiveData<SpecialityResult> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getSpeciality(): LiveData<SpecialityResult> = specialityList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadSpeciality() {
        var apiClient = ApiClient()
        var apiCall = apiClient.getSpeciality()
        apiCall.enqueue(object: Callback<SpecialityResult> {
            override fun onFailure(call: Call<SpecialityResult>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<SpecialityResult>, response: Response<SpecialityResult>) {
                loading.value = false
                specialityList.value = response.body()
            }
        })
    }
}