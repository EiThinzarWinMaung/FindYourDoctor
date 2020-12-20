package com.etzwm.healthcareapp.ui.recommend_suggest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.etzwm.healthcareapp.model.recommend.Recommend
import kotlinx.android.synthetic.main.fragment_recommend_suggest.*
import kotlin.math.log

class RecommendSuggestFragment : Fragment() {

//    private var hospitalArray: List<Hospital> = ArrayList()
    lateinit var recommendSuggestAdapter: RecommendSuggestAdapter
    lateinit var recommendSuggestViewmodel: RecommendSuggestViewmodel
//    lateinit var spinnerRecommend: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_suggest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        spinnerRecommend = view.findViewById(R.id.spinnerRecommend)

        recommendSuggestViewmodel = ViewModelProvider(this).get(RecommendSuggestViewmodel::class.java)

        recommendSuggestAdapter = RecommendSuggestAdapter()

        RecyclerviewRecommendSuggest.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = recommendSuggestAdapter
        }

        observeViewmodel()

        btnCreate.setOnClickListener {
            var action = RecommendSuggestFragmentDirections.actionNavigationRecommendSuggestToRecommendSuggestWriteFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeViewmodel() {
        recommendSuggestViewmodel.getRecommendSuggest().observe(viewLifecycleOwner, Observer { result ->
//            var recommendSpinnerResultList: List<Recommend> = ArrayList<Recommend>()
//            for(i in 0..result.recommends.lastIndex) {
//                if(result.recommends.get(i).hospital.hospital_id == 1) {
//                    Log.d(">>>>>>>>", result.recommends.get(i).recommend_id.toString())
//                    Log.d(">>>>>>>>>", result.recommends.get(i).toString())
//                }
//            }
//            Log.d(">>>>>>>>>", result.recommends.toString())
            recommendSuggestAdapter.updateRecommendSuggest(result.recommends)
        })

        recommendSuggestViewmodel.getLoading().observe(viewLifecycleOwner, Observer { load ->
            BlueLoading.visibility =
                if (load) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
        })
    }

    override fun onResume() {
        super.onResume()
        recommendSuggestViewmodel.loadRecommendSuggest()
    }
}