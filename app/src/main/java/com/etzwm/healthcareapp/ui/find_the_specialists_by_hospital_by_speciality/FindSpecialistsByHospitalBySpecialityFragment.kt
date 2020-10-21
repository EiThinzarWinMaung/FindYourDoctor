package com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital_by_speciality

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
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.hospitaldetails.Speciality
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_hospital_by_speciality.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_hospital_by_speciality.BlueLoading

class FindSpecialistsByHospitalBySpecialityFragment : Fragment(), FindSpecialistsByHospitalBySpecialityAdapter.ClickListener {

    lateinit var findSpecialistsByHospitalBySpecialityAdapter: FindSpecialistsByHospitalBySpecialityAdapter
    lateinit var findSpecialistsByHospitalBySpecialityViewmodel: FindSpecialistsByHospitalBySpecialityViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_find_specialists_by_hospital_by_speciality,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findSpecialistsByHospitalBySpecialityViewmodel = ViewModelProvider(this).get(FindSpecialistsByHospitalBySpecialityViewmodel::class.java)

        findSpecialistsByHospitalBySpecialityAdapter = FindSpecialistsByHospitalBySpecialityAdapter()

        RecyclerviewSearchByHospitalBySpeciality.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = findSpecialistsByHospitalBySpecialityAdapter
        }

        findSpecialistsByHospitalBySpecialityAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        findSpecialistsByHospitalBySpecialityViewmodel.getSpecialityByHospital().observe(viewLifecycleOwner, Observer { specialityByHospital ->
            findSpecialistsByHospitalBySpecialityAdapter.updateSpecialityByHospital(specialityByHospital.hospital.specialities)
            txtBannerSearchByHospitalBySpeciality.text = specialityByHospital.hospital.hospital_name
            Picasso.get().load(imgURL + specialityByHospital.hospital.hospital_banner).placeholder(R.drawable.placeholder_image).into(imgBannerSearchByHospitalBySpeciality)
        })

        findSpecialistsByHospitalBySpecialityViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
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

        var specialityByHospitalArgs = arguments?.let {
            FindSpecialistsByHospitalBySpecialityFragmentArgs.fromBundle(it)
        }

        var specialityByHospitalId = specialityByHospitalArgs?.hospitalID

        findSpecialistsByHospitalBySpecialityViewmodel.loadSpecialityByHospital(specialityByHospitalId!!)
    }

    override fun onClick(speciality: Speciality) {
        var specialityByHospitalArgs = arguments?.let {
            FindSpecialistsByHospitalBySpecialityFragmentArgs.fromBundle(it)
        }
        var specialityByHospitalId = specialityByHospitalArgs?.hospitalID

        var action = FindSpecialistsByHospitalBySpecialityFragmentDirections.actionFindSpecialistsByHospitalBySpecialityFragmentToFindSpecialistsByHospitalBySpecialityByPhysicianFragment(speciality.speciality_id,specialityByHospitalId!!)
        findNavController().navigate(action)
    }
}