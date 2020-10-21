package com.etzwm.healthcareapp.ui.find_the_specialists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.etzwm.healthcareapp.R
import kotlinx.android.synthetic.main.fragment_find_specialists.*

class FindSpecialistsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_specialists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SearchSpecialityCard.setOnClickListener {
            var action = FindSpecialistsFragmentDirections.actionNavigationFindSpecialistsToFindSpecialistsBySpecialityFragment()
            findNavController().navigate(action)
        }

        SearchHospitalCard.setOnClickListener {
            var action = FindSpecialistsFragmentDirections.actionNavigationFindSpecialistsToFindSpecialistsByHospitalFragment()
            findNavController().navigate(action)
        }

        SearchPhysiciansCard.setOnClickListener {
            var action = FindSpecialistsFragmentDirections.actionNavigationFindSpecialistsToFindSpecialistsByNameFragment()
            findNavController().navigate(action)
        }
    }
}