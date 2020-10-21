package com.etzwm.healthcareapp.ui.medical_checkup_packages_by_hospital

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
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.etzwm.healthcareapp.ui.hospital_contacts.HospitalContactsViewmodel
import kotlinx.android.synthetic.main.fragment_medical_checkup_packages_by_hospital.*
import kotlinx.android.synthetic.main.fragment_medical_checkup_packages_by_hospital.BlueLoading

class MedicalCheckupPackagesByHospitalFragment : Fragment(), MedicalCheckupPackagesByHospitalAdapter.ClickListener {

    lateinit var medicalCheckupPackagesByHospitalAdapter: MedicalCheckupPackagesByHospitalAdapter
    lateinit var hospitalContactsViewmodel: HospitalContactsViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_medical_checkup_packages_by_hospital,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalContactsViewmodel = ViewModelProvider(this).get(HospitalContactsViewmodel::class.java)

        medicalCheckupPackagesByHospitalAdapter = MedicalCheckupPackagesByHospitalAdapter()

        RecyclerviewMedicalCheckupPackagesByHospital.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = medicalCheckupPackagesByHospitalAdapter
        }
        medicalCheckupPackagesByHospitalAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        hospitalContactsViewmodel.getHospital().observe(viewLifecycleOwner, Observer { hospital ->
            medicalCheckupPackagesByHospitalAdapter.updateMedicalCheckupPackages(hospital.hospitals)
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
        var action = MedicalCheckupPackagesByHospitalFragmentDirections.actionMedicalCheckupPackagesByHospitalFragmentToNavigationMedicalCheckupPackages(hospital.hospital_id)
        findNavController().navigate(action)
    }
}