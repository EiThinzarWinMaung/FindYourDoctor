package com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_find_specialists_by_hospital.view.*

class FindSpecialistsByHospitalAdapter(var hospitalList: List<Hospital> = ArrayList<Hospital>()): RecyclerView.Adapter<FindSpecialistsByHospitalAdapter.FindSpecialistsByHospitalViewHolder>() {

    var hclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: FindSpecialistsByHospitalFragment) {
        this.hclickListener = clickListener
    }

    inner class FindSpecialistsByHospitalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var hospital: Hospital

        init {
            itemView.setOnClickListener(this)
        }

        fun bindHospital(hospital: Hospital) {
            this.hospital = hospital
            itemView.txtSearchByHospital.text = hospital.hospital_name
            Picasso.get().load(imgURL + hospital.hospital_image).placeholder(R.drawable.placeholder_image).into(itemView.imgSearchByHospital)
        }

        override fun onClick(p0: View?) {
            hclickListener?.onClick(hospital)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindSpecialistsByHospitalViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_specialists_by_hospital, parent, false)
        return FindSpecialistsByHospitalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: FindSpecialistsByHospitalViewHolder, position: Int) {
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