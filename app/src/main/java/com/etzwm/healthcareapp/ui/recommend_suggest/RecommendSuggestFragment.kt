package com.etzwm.healthcareapp.ui.recommend_suggest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etzwm.healthcareapp.R
import kotlinx.android.synthetic.main.fragment_recommend_suggest.*
import kotlinx.android.synthetic.main.fragment_recommend_suggest.BlueLoading

class RecommendSuggestFragment : Fragment() {

    lateinit var recommendSuggestAdapter: RecommendSuggestAdapter
    lateinit var recommendSuggestViewmodel: RecommendSuggestViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_suggest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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