package com.etzwm.healthcareapp.ui.recommend_suggest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.recommend.RecommendResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendSuggestViewmodel: ViewModel() {

    private var recommendList: MutableLiveData<RecommendResult> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getRecommendSuggest(): LiveData<RecommendResult> = recommendList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadRecommendSuggest() {
        var apiClient = ApiClient()
        var apiCall = apiClient.getRecommendSuggest()
        apiCall.enqueue(object: Callback<RecommendResult> {
            override fun onFailure(call: Call<RecommendResult>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<RecommendResult>, response: Response<RecommendResult>) {
                loading.value = false
                recommendList.value = response.body()
            }
        })
    }
}