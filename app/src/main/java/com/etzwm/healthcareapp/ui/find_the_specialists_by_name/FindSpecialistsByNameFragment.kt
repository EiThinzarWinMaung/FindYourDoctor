package com.etzwm.healthcareapp.ui.find_the_specialists_by_name

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
import androidx.core.widget.addTextChangedListener
import com.etzwm.healthcareapp.model.physicians.Physician
import kotlinx.android.synthetic.main.fragment_find_specialists_by_name.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_name.BlueLoading

class FindSpecialistsByNameFragment : Fragment(), FindSpecialistsByNameAdapter.ClickListener {

    lateinit var findSpecialistsByNameAdapter: FindSpecialistsByNameAdapter
    lateinit var findSpecialistsByNameViewmodel: FindSpecialistsByNameViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_specialists_by_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findSpecialistsByNameViewmodel = ViewModelProvider(this).get(FindSpecialistsByNameViewmodel::class.java)

        findSpecialistsByNameAdapter = FindSpecialistsByNameAdapter()

        RecyclerviewSearchByName.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = findSpecialistsByNameAdapter
        }
        findSpecialistsByNameAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        findSpecialistsByNameViewmodel.getPhysician().observe(viewLifecycleOwner, Observer { physician ->
            findSpecialistsByNameAdapter.updatePhysician(physician.physicians)
        })

        findSpecialistsByNameViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            BlueLoading.visibility =
                if (isLoading) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
        })
    }

    override fun onResume() {
        super.onResume()
        findSpecialistsByNameViewmodel.loadPhysician()

        var searchValue = edtSearchName.text
        edtSearchName.addTextChangedListener() {
            Log.d("SearchItem >>>>>>>>", searchValue.toString())
            findSpecialistsByNameViewmodel.loadPhysicianSearchName(searchValue.toString())
        }
    }

    override fun onClick(physician: Physician) {
        var action = FindSpecialistsByNameFragmentDirections.actionFindSpecialistsByNameFragmentToPhysicianDetailsFragment(physician.id)
        findNavController().navigate(action)
    }
}