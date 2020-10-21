package com.etzwm.healthcareapp.ui.physician_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.etzwm.healthcareapp.R
import kotlinx.android.synthetic.main.fragment_physician_details.*
import kotlinx.android.synthetic.main.fragment_physician_details.BlueLoading

class PhysicianDetailsFragment : Fragment() {

    lateinit var physicianDetailsAdapter: PhysicianDetailsAdapter
    lateinit var physicianDetailsViewmodel: PhysicianDetailsViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_physician_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        physicianDetailsViewmodel = ViewModelProvider(this).get(PhysicianDetailsViewmodel::class.java)

        physicianDetailsAdapter = PhysicianDetailsAdapter()

        RecyclerviewPhysicianDetails.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = physicianDetailsAdapter
        }
        observeViewmodel()
    }

    private fun observeViewmodel() {
        physicianDetailsViewmodel.getPhysicianDetails().observe(viewLifecycleOwner, Observer { physicianDetails ->
            physicianDetailsAdapter.updatePhysicianDetails(physicianDetails.physician_schedule)
            txtPhysicianDetailsName.text = physicianDetails.physician_schedule.get(0).physician.name
            txtPhysicianDetailsSpeciality.text = physicianDetails.physician_schedule.get(0).physician.speciality.speciality_mname
            txtPhysicianDetailsDegree.text = physicianDetails.physician_schedule.get(0).physician.degree
        })

        physicianDetailsViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
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

        var physicianDetailsArgs = arguments?.let {
            PhysicianDetailsFragmentArgs.fromBundle(it)
        }

        var physicianDetailsId = physicianDetailsArgs?.physicianID

        physicianDetailsViewmodel.loadPhysicianDetails(physicianDetailsId!!)
    }
}