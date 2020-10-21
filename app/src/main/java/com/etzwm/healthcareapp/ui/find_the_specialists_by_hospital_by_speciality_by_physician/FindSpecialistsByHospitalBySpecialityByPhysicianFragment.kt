package com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital_by_speciality_by_physician

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
import kotlinx.android.synthetic.main.fragment_find_specialists_by_hospital.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_hospital_by_speciality_by_physician.*
import kotlinx.android.synthetic.main.fragment_find_specialists_by_hospital_by_speciality_by_physician.BlueLoading

class FindSpecialistsByHospitalBySpecialityByPhysicianFragment : Fragment(), FindSpecialistsByHospitalBySpecialityByPhysicianAdapter.ClickListener {

    lateinit var findSpecialistsByHospitalBySpecialityByPhysicianAdapter: FindSpecialistsByHospitalBySpecialityByPhysicianAdapter
    lateinit var findSpecialistsByHospitalBySpecialityByPhysicianViewmodel: FindSpecialistsByHospitalBySpecialityByPhysicianViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_find_specialists_by_hospital_by_speciality_by_physician,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findSpecialistsByHospitalBySpecialityByPhysicianViewmodel = ViewModelProvider(this).get(FindSpecialistsByHospitalBySpecialityByPhysicianViewmodel::class.java)

        findSpecialistsByHospitalBySpecialityByPhysicianAdapter = FindSpecialistsByHospitalBySpecialityByPhysicianAdapter()

        RecyclerviewSearchByHospitalBySpecialityByPhysician.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = findSpecialistsByHospitalBySpecialityByPhysicianAdapter
        }

        findSpecialistsByHospitalBySpecialityByPhysicianAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        findSpecialistsByHospitalBySpecialityByPhysicianViewmodel.getPhysicianBySpecialityByHospital().observe(viewLifecycleOwner, Observer { physician ->
            findSpecialistsByHospitalBySpecialityByPhysicianAdapter.updatePhysicianBySpecialityByHospital(physician.physicians)
            txtBannerSearchByHospitalBySpecialityByPhysician1.text = physician.physicians.get(0).speciality.speciality_mname
        })

        findSpecialistsByHospitalBySpecialityByPhysicianViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
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

        var physicianBySpecialityByHospitalArgs = arguments?.let {
            FindSpecialistsByHospitalBySpecialityByPhysicianFragmentArgs.fromBundle(it)
        }

        var specialityId = physicianBySpecialityByHospitalArgs?.specialityID
        var hospitalId = physicianBySpecialityByHospitalArgs?.hospitalID

        findSpecialistsByHospitalBySpecialityByPhysicianViewmodel.loadPhysicianBySpecialityByHospital(specialityId!!,hospitalId!!)

        if(hospitalId == 1) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "ကန်သာယာဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_kan_thar_yar)
        }
        if(hospitalId == 2) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "ပင်လုံဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_pinlon)
        }
        if(hospitalId == 3) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "ပန်းလှိုင်ဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_pun_hlaing_siloam)
        }
        if(hospitalId == 4) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "ဗဟိုစည်ဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_bahosi)
        }
        if(hospitalId == 5) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "ဝိတိုရိယဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_victoria)
        }
        if(hospitalId == 6) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "သုခကမ္ဘာဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_thukha_gabar)
        }
        if(hospitalId == 7) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "အာယု အပြည်ပြည်ဆိုင်ရာဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_aryu)
        }
        if(hospitalId == 8) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "အာရှတော်ဝင်ဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_asia_royal)
        }
        if(hospitalId == 9) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "အောင်ရတနာဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_aung_yadana)
        }
        if(hospitalId == 10) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "Grand Hantha ဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_grand_hantha)
        }
        if(hospitalId == 11) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "OSC ဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_osc)
        }
        if(hospitalId == 12) {
            txtBannerSearchByHospitalBySpecialityByPhysician.text = "SSC ဆေးရုံ"
            imgBannerSearchByHospitalBySpecialityByPhysician.setImageResource(R.drawable.banner_ssc)
        }
    }

    override fun onClick(physician: Physician) {
        var action = FindSpecialistsByHospitalBySpecialityByPhysicianFragmentDirections.actionFindSpecialistsByHospitalBySpecialityByPhysicianFragmentToPhysicianDetailsFragment(physician.id)
        findNavController().navigate(action)
    }
}