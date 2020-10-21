package com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.etzwm.healthcareapp.ui.hospital_contacts.HospitalContactsViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_hospital.*

class FindSpecialistsByHospitalFragment : Fragment(), FindSpecialistsByHospitalAdapter.ClickListener {

    lateinit var findSpecialistsByHospitalAdapter: FindSpecialistsByHospitalAdapter
    lateinit var hospitalContactsViewmodel: HospitalContactsViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_specialists_by_hospital, container, false)

//        FragmentManager.POP_BACK_STACK_INCLUSIVE
//        AppToolbar.navigationIcon?.isVisible = false
//        AppToolbar.navigationIcon?.setVisible(false, true)
//        AppToolbar.setNavigationIcon(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalContactsViewmodel = ViewModelProvider(this).get(HospitalContactsViewmodel::class.java)

        findSpecialistsByHospitalAdapter = FindSpecialistsByHospitalAdapter()

        RecyclerviewSearchByHospital.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = findSpecialistsByHospitalAdapter
        }

        findSpecialistsByHospitalAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        hospitalContactsViewmodel.getHospital().observe(viewLifecycleOwner, Observer {hospital ->
            findSpecialistsByHospitalAdapter.updateHospital(hospital.hospitals)
        })

        hospitalContactsViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
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
        hospitalContactsViewmodel.loadHospital()
    }

    override fun onClick(hospital: Hospital) {
        var action = FindSpecialistsByHospitalFragmentDirections.actionFindSpecialistsByHospitalFragmentToFindSpecialistsByHospitalBySpecialityFragment(hospital.hospital_id)
        findNavController().navigate(action)
    }
}