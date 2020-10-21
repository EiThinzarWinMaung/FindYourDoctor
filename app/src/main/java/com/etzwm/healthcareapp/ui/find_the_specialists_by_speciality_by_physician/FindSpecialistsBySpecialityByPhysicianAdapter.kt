package com.etzwm.healthcareapp.ui.find_the_specialists_by_speciality_by_physician

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.model.physicians.Physician
import kotlinx.android.synthetic.main.item_find_specialists_by_speciality_by_physician.view.*

class FindSpecialistsBySpecialityByPhysicianAdapter(var physicianListBySpeciality: List<Physician> = ArrayList<Physician>()): RecyclerView.Adapter<FindSpecialistsBySpecialityByPhysicianAdapter.FindSpecialistsBySpecialityByPhysicianViewHolder>() {

    var pclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: FindSpecialistsBySpecialityByPhysicianFragment) {
        this.pclickListener = clickListener
    }

    inner class FindSpecialistsBySpecialityByPhysicianViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var physician: Physician

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(physician: Physician) {
            this.physician = physician
            itemView.txtSearchBySpecialityByPhysicianName.text = physician.name
            itemView.txtSearchBySpecialityByPhysicianDegree.text = physician.degree
        }

        override fun onClick(p0: View?) {
            pclickListener?.onClick(physician)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindSpecialistsBySpecialityByPhysicianViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_specialists_by_speciality_by_physician, parent, false)
        return FindSpecialistsBySpecialityByPhysicianViewHolder(view)
    }

    override fun getItemCount(): Int {
        return physicianListBySpeciality.size
    }

    override fun onBindViewHolder(holder: FindSpecialistsBySpecialityByPhysicianViewHolder, position: Int) {
        return holder.bind(physicianListBySpeciality[position])
    }

    fun updatePhysicianBySpeciality(physicianListBySpeciality: List<Physician>) {
        this.physicianListBySpeciality = physicianListBySpeciality
    }

    interface ClickListener {
        fun onClick(physician: Physician)
    }
}