package com.etzwm.healthcareapp.ui.hospital_contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hospital_contacts.view.*

class HospitalContactsAdapter(var hospitalList: List<Hospital> = ArrayList<Hospital>()): RecyclerView.Adapter<HospitalContactsAdapter.HospitalContactsViewHolder>() {

    class HospitalContactsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindHospital(hospital: Hospital) {
            itemView.txtHospitalName.text = hospital.hospital_name
            itemView.txtHospitalAddress.text = hospital.place
            itemView.txtHospitalPhone.text = hospital.phone_no
            itemView.txtHospitalFacebook.text = hospital.facebook_link
            if (hospital.email == "-") {
                itemView.MailIcon.isVisible = false
            }
            else {
                itemView.MailIcon.isVisible = true
                itemView.txtHospitalMail.text = hospital.email
            }
            itemView.txtHospitalWebsite.text = hospital.website_link
            Picasso.get().load(imgURL + hospital.hospital_banner).placeholder(R.drawable.placeholder_image).into(itemView.imgHospitalBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalContactsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital_contacts, parent, false)
        return HospitalContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: HospitalContactsViewHolder, position: Int) {
        return holder.bindHospital(hospitalList[position])
    }

    fun updateHospital(hospitalList: List<Hospital>) {
        this.hospitalList = hospitalList
        notifyDataSetChanged()
    }
}
