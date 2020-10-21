package com.etzwm.healthcareapp.ui.find_the_specialists_by_hospital_by_speciality_by_physician

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.model.physicians.Physician
import kotlinx.android.synthetic.main.item_find_specialists_by_hospital_by_speciality_by_physician.view.*

class FindSpecialistsByHospitalBySpecialityByPhysicianAdapter(var physicianListBySpecialityByHospital: List<Physician> = ArrayList<Physician>()): RecyclerView.Adapter<FindSpecialistsByHospitalBySpecialityByPhysicianAdapter.FindSpecialistsByHospitalBySpecialityByPhysicianViewHolder> (){

    var pclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: FindSpecialistsByHospitalBySpecialityByPhysicianFragment) {
        this.pclickListener = clickListener
    }

    inner class FindSpecialistsByHospitalBySpecialityByPhysicianViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var physician: Physician

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(physician: Physician) {
            this.physician = physician
            itemView.txtSearchByHospitalBySpecialityByPhysicianName.text = physician.name
            itemView.txtSearchByHospitalBySpecialityByPhysicianDegree.text = physician.degree
        }

        override fun onClick(p0: View?) {
            pclickListener?.onClick(physician)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindSpecialistsByHospitalBySpecialityByPhysicianViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_specialists_by_hospital_by_speciality_by_physician, parent, false)
        return FindSpecialistsByHospitalBySpecialityByPhysicianViewHolder(view)
    }

    override fun getItemCount(): Int {
        return physicianListBySpecialityByHospital.size
    }

    override fun onBindViewHolder(holder: FindSpecialistsByHospitalBySpecialityByPhysicianViewHolder, position: Int) {
        return holder.bind(physicianListBySpecialityByHospital[position])
    }

    fun updatePhysicianBySpecialityByHospital(physicianListBySpecialityByHospital: List<Physician>) {
        this.physicianListBySpecialityByHospital = physicianListBySpecialityByHospital
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(physician: Physician)
    }
}