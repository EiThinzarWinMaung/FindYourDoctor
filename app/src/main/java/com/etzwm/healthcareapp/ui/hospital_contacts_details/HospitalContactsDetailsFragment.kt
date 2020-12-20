package com.etzwm.healthcareapp.ui.hospital_contacts_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital_by_speciality.FindSpecialistsByHospitalBySpecialityViewmodel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hospital_contacts_details.*

class HospitalContactsDetailsFragment : Fragment() {

    lateinit var findSpecialistsByHospitalBySpecialityViewmodel: FindSpecialistsByHospitalBySpecialityViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital_contacts_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findSpecialistsByHospitalBySpecialityViewmodel = ViewModelProvider(this).get(FindSpecialistsByHospitalBySpecialityViewmodel::class.java)

        findSpecialistsByHospitalBySpecialityViewmodel.getSpecialityByHospital().observe(viewLifecycleOwner, Observer { hospital ->
            txtHospitalContactsDetailsName.text = hospital.hospital.hospital_name
            txtHospitalContactsDetailsAddress.text = hospital.hospital.place
            txtHospitalContactsDetailsPhone.text = hospital.hospital.phone_no
            txtHospitalContactsDetailsFacebook.text = hospital.hospital.facebook_link
            txtHospitalContactsDetailsWebsite.text = hospital.hospital.website_link
            if(hospital.hospital.email == "-") {
                MailIcon.visibility = View.GONE
                txtHospitalContactsDetailsMail.visibility = View.GONE
            }
            else {
                MailIcon.visibility = View.VISIBLE
                txtHospitalContactsDetailsMail.visibility = View.VISIBLE
                txtHospitalContactsDetailsMail.text = hospital.hospital.email
            }
            Picasso.get().load(imgURL + hospital.hospital.hospital_image).placeholder(R.drawable.placeholder_image).into(imgHospitalContactsDetailsLogo)
            Picasso.get().load(imgURL + hospital.hospital.hospital_banner).placeholder(R.drawable.placeholder_image).into(imgHospitalContactsDetailsBanner)

            HospitalContactsDetailsGoogleMap.setOnClickListener {
                if(hospital.hospital.hospital_id == 1) {
                    val uri = "geo:16.8421538,96.1345625?q=Kan Thar Yar Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 2) {
                    val uri = "geo:16.8610368,96.2070781?q=Pin Lon Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 3) {
                    val uri = "geo:16.8414427,96.087768?q=Pun Hlaing Siloam Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 4) {
                    val uri = "geo:16.7795469,96.1376905?q=Bahosi Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 5) {
                    val uri = "geo:16.8777369,96.1277469?q=Victoria Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 6) {
                    val uri = "geo:16.832965,96.1284198?q=Thukha Gabar Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 7) {
                    val uri = "geo:16.8128987,96.1736495?q=Aryu Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 8) {
                    val uri = "geo:16.7982323,96.129046?q=Asia Royal Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 9) {
                    val uri = "geo:16.8324188,96.1772561?q=Aung Yadanar Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 10) {
                    val uri = "geo:16.821268,96.1210101?q=Grand Hantha Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 11) {
                    val uri = "geo:16.8848186,96.1528831?q=OSC Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
                if(hospital.hospital.hospital_id == 11) {
                    val uri = "geo:16.8110973,96.1641659?q=SSC Hospital"
                    val uriMap = Uri.parse(uri)
                    val intent = Intent(Intent.ACTION_VIEW,uriMap)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
            }
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

        var hospitalContactsDetailsArgs = arguments?.let {
            HospitalContactsDetailsFragmentArgs.fromBundle(it)
        }

        var hospitalContactsId = hospitalContactsDetailsArgs?.hospitalID
        findSpecialistsByHospitalBySpecialityViewmodel.loadSpecialityByHospital(hospitalContactsId!!)
    }
}