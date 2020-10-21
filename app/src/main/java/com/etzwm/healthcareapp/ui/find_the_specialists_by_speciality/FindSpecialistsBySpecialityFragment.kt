package com.etzwm.healthcareapp.ui.find_the_specialists_by_speciality

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
import com.etzwm.healthcareapp.model.specialities.Speciality
import kotlinx.android.synthetic.main.fragment_find_specialists_by_speciality.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_speciality.BlueLoading

class FindSpecialistsBySpecialityFragment : Fragment(), FindSpecialistsBySpecialityAdapter.ClickListener {

    lateinit var specialistsBySpecialityAdapter: FindSpecialistsBySpecialityAdapter
    lateinit var specialistsBySpecialityViewmodel: FindSpecialistsBySpecialityViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_specialists_by_speciality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        specialistsBySpecialityViewmodel = ViewModelProvider(this).get(FindSpecialistsBySpecialityViewmodel::class.java)

        specialistsBySpecialityAdapter = FindSpecialistsBySpecialityAdapter()

        RecyclerviewSearchBySpeciality.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = specialistsBySpecialityAdapter
        }

        specialistsBySpecialityAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        specialistsBySpecialityViewmodel.getSpeciality().observe(viewLifecycleOwner, Observer { speciality ->
            specialistsBySpecialityAdapter.updateSpeciality(speciality.specialities)
        })

        specialistsBySpecialityViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
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
        specialistsBySpecialityViewmodel.loadSpeciality()
    }

    override fun onClick(speciality: Speciality) {
        var action = FindSpecialistsBySpecialityFragmentDirections.actionFindSpecialistsBySpecialityFragmentToFindSpecialistsBySpecialityByPhysicianFragment(speciality.speciality_id)
        findNavController().navigate(action)
    }
}