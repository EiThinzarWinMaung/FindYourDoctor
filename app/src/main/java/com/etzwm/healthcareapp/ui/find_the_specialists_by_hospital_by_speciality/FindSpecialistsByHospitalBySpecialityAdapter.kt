package com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital_by_speciality

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.hospitaldetails.Speciality
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_find_specialists_by_hospital_by_speciality.view.*
import kotlinx.android.synthetic.main.item_find_specialists_by_hospital_by_speciality.view.*
import kotlinx.android.synthetic.main.item_find_specialists_by_speciality.view.*

class FindSpecialistsByHospitalBySpecialityAdapter(var specialityByHospitalList: List<Speciality> = ArrayList<Speciality>()): RecyclerView.Adapter<FindSpecialistsByHospitalBySpecialityAdapter.FindSpecialistsByHospitalBySpecialityViewHolder>() {

    var phclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: FindSpecialistsByHospitalBySpecialityFragment) {
        this.phclickListener = clickListener
    }

    inner class FindSpecialistsByHospitalBySpecialityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var speciality: Speciality

        init {
            itemView.setOnClickListener(this)
        }

        fun bindSpecialityByHospital(speciality: Speciality) {
            this.speciality = speciality
            itemView.txtSearchByHospitalBySpecialityMM.text = speciality.speciality_mname
            Picasso.get().load(imgURL + speciality.speciality_image).placeholder(R.drawable.placeholder_image).into(itemView.imgSearchByHospitalBySpeciality)
        }

        override fun onClick(p0: View?) {
            phclickListener?.onClick(speciality)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindSpecialistsByHospitalBySpecialityViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_specialists_by_hospital_by_speciality, parent, false)
        return FindSpecialistsByHospitalBySpecialityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return specialityByHospitalList.size
    }

    override fun onBindViewHolder(holder: FindSpecialistsByHospitalBySpecialityViewHolder, position: Int) {
        return holder.bindSpecialityByHospital(specialityByHospitalList[position])
    }

    fun updateSpecialityByHospital(specialityByHospitalList: List<Speciality>) {
        this.specialityByHospitalList = specialityByHospitalList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(speciality: Speciality)
    }
}