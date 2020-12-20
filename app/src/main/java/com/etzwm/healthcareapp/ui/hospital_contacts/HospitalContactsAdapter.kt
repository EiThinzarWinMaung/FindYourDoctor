package com.etzwm.healthcareapp.ui.hospital_contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hospital_contacts.view.*
import java.util.*
import kotlin.collections.ArrayList

class HospitalContactsAdapter(var hospitalList: List<Hospital> = ArrayList<Hospital>()): RecyclerView.Adapter<HospitalContactsAdapter.HospitalContactsViewHolder>() {

    var hclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: HospitalContactsFragment) {
        this.hclickListener = clickListener
    }

    inner class HospitalContactsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var hospital: Hospital

        init {
            itemView.setOnClickListener(this)
        }

        fun bindHospital(hospital: Hospital) {
            this.hospital = hospital
            itemView.txtHospitalContactsList.text = hospital.hospital_name
            Picasso.get().load(imgURL + hospital.hospital_image).placeholder(R.drawable.placeholder_image).into(itemView.imgHospitalContactsList)
        }

        override fun onClick(p0: View?) {
            hclickListener?.onClick(hospital)
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

    interface ClickListener {
        fun onClick(hospital: Hospital)
    }
}
