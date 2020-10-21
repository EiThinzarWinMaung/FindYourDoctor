package com.etzwm.healthcareapp.ui.medical_checkup_packages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_medical_checkup_packages.*
import kotlinx.android.synthetic.main.fragment_medical_checkup_packages.BlueLoading

class MedicalCheckupPackagesFragment : Fragment() {

    lateinit var medicalCheckupPackagesAdapter: MedicalCheckupPackagesAdapter
    lateinit var medicalCheckupPackagesViewmodel: MedicalCheckupPackagesViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_checkup_packages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicalCheckupPackagesViewmodel = ViewModelProvider(this).get(MedicalCheckupPackagesViewmodel::class.java)

        medicalCheckupPackagesAdapter = MedicalCheckupPackagesAdapter()

        RecyclerviewMedicalCheckupPackagesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = medicalCheckupPackagesAdapter
        }
        observeViewmodel()
    }

    private fun observeViewmodel() {
        medicalCheckupPackagesViewmodel.getMedicalCheckupPackages().observe(viewLifecycleOwner, Observer { medicalCheckupPackages ->
            medicalCheckupPackagesAdapter.updateMedicalCheckupPackagesList(medicalCheckupPackages.packages)
            txtBannerMedicalCheckupPackagesList.text = medicalCheckupPackages.packages.get(1).hospital.hospital_name
            Picasso.get().load(imgURL + medicalCheckupPackages.packages.get(1).hospital.hospital_banner).placeholder(R.drawable.placeholder_image).into(imgBannerMedicalCheckupPackagesList)
        })

        medicalCheckupPackagesViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
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

        var medicalCheckupPackagesArgs = arguments?.let {
            MedicalCheckupPackagesFragmentArgs.fromBundle(it)
        }

        var medicalCheckupPackagesId = medicalCheckupPackagesArgs?.hospitalID

        medicalCheckupPackagesViewmodel.loadMedicalCheckupPackages(medicalCheckupPackagesId!!)
    }
}