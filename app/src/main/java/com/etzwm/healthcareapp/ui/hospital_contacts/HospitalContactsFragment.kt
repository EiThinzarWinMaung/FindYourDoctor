package com.etzwm.healthcareapp.ui.hospital_contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.etzwm.healthcareapp.R
import kotlinx.android.synthetic.main.fragment_hospital_contacts.*
import kotlinx.android.synthetic.main.fragment_hospital_contacts.BlueLoading

class HospitalContactsFragment : Fragment() {

    lateinit var hospitalContactsAdapter: HospitalContactsAdapter
    lateinit var hospitalContactsViewmodel: HospitalContactsViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalContactsViewmodel = ViewModelProvider(this).get(HospitalContactsViewmodel::class.java)

        hospitalContactsAdapter = HospitalContactsAdapter()

        RecyclerviewHospitalContacts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = hospitalContactsAdapter
        }

        observeViewmodel()
    }

    private fun observeViewmodel() {
        hospitalContactsViewmodel.getHospital().observe(viewLifecycleOwner, Observer { hospital ->
            hospitalContactsAdapter.updateHospital(hospital.hospitals)
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
}