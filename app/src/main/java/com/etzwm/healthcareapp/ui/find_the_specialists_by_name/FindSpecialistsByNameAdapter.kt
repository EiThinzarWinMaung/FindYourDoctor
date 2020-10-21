package com.etzwm.healthcareapp.ui.find_the_specialists_by_name

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.model.physicians.Physician
import com.etzwm.healthcareapp.model.schedule.Schedule
import kotlinx.android.synthetic.main.item_find_specialists_by_name.view.*

class FindSpecialistsByNameAdapter(var physicianList: List<Physician> = ArrayList<Physician>()): RecyclerView.Adapter<FindSpecialistsByNameAdapter.FindSpecialistsByNameViewHolder>() {

    var dclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: FindSpecialistsByNameFragment) {
        this.dclickListener = clickListener
    }

    inner class FindSpecialistsByNameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var physician: Physician

        init {
            itemView.setOnClickListener(this)
        }

        fun bindPhysician(physician: Physician) {
            this.physician = physician
            itemView.txtSearchByNamePhysician.text = physician.name
            itemView.txtSearchByNameSpeciality.text = physician.speciality.speciality_mname
        }

        override fun onClick(p0: View?) {
            dclickListener?.onClick(physician)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindSpecialistsByNameViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_specialists_by_name, parent, false)
        return FindSpecialistsByNameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return physicianList.size
    }

    override fun onBindViewHolder(holder: FindSpecialistsByNameViewHolder, position: Int) {
        return holder.bindPhysician(physicianList[position])
    }

    fun updatePhysician(physicianList: List<Physician>) {
        this.physicianList = physicianList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(physician: Physician)
    }
}