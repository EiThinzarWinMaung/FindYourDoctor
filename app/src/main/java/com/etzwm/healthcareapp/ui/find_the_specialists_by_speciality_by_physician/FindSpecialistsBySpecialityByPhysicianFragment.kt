package com.etzwm.healthcareapp.ui.find_the_specialists_by_speciality_by_physician

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.model.physicians.Physician
import kotlinx.android.synthetic.main.fragment_find_specialists_by_speciality_by_physician.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_speciality_by_physician.BlueLoading

class FindSpecialistsBySpecialityByPhysicianFragment : Fragment(), FindSpecialistsBySpecialityByPhysicianAdapter.ClickListener {

    lateinit var findSpecialistsBySpecialityByPhysicianAdapter: FindSpecialistsBySpecialityByPhysicianAdapter
    lateinit var findSpecialistsBySpecialityByPhysicianViewmodel: FindSpecialistsBySpecialityByPhysicianViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_find_specialists_by_speciality_by_physician,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findSpecialistsBySpecialityByPhysicianViewmodel = ViewModelProvider(this).get(FindSpecialistsBySpecialityByPhysicianViewmodel::class.java)

        findSpecialistsBySpecialityByPhysicianAdapter = FindSpecialistsBySpecialityByPhysicianAdapter()

        RecyclerviewSearchBySpecialityByPhysician.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = findSpecialistsBySpecialityByPhysicianAdapter
        }

        findSpecialistsBySpecialityByPhysicianAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        findSpecialistsBySpecialityByPhysicianViewmodel.getPhysicianBySpeciality().observe(viewLifecycleOwner, Observer { physician ->
            findSpecialistsBySpecialityByPhysicianAdapter.updatePhysicianBySpeciality(physician.physicians)
            txtBannerSearchBySpecialityByPhysician.text = physician.physicians.get(0).speciality.speciality_mname
        })

        findSpecialistsBySpecialityByPhysicianViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
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

        var physicianBySpecialityArgs = arguments?.let {
            FindSpecialistsBySpecialityByPhysicianFragmentArgs.fromBundle(it)
        }

        var physicianBySpecialityId = physicianBySpecialityArgs?.specialityID

        findSpecialistsBySpecialityByPhysicianViewmodel.loadPhysicianBySpeciality(physicianBySpecialityId!!)
    }

    override fun onClick(physician: Physician) {
        var action = FindSpecialistsBySpecialityByPhysicianFragmentDirections.actionFindSpecialistsBySpecialityByPhysicianFragmentToPhysicianDetailsFragment(physician.id)
        findNavController().navigate(action)
    }
}