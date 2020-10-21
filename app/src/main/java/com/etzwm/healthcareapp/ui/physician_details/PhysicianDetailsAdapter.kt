package com.etzwm.healthcareapp.ui.physician_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.physiciandetails.PhysicianSchedule
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_physician_details.view.*

class PhysicianDetailsAdapter(var physicianDetailsList: List<PhysicianSchedule> = ArrayList<PhysicianSchedule>()): RecyclerView.Adapter<PhysicianDetailsAdapter.PhysicianDetailsViewHolder>() {

    class PhysicianDetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindPhysicianDetails(physicianSchedule: PhysicianSchedule) {
            itemView.txtPhysicianDetailsDateTime.text = physicianSchedule.date_time
            itemView.txtPhysicianDetailsHospitalName.text = physicianSchedule.hospital.hospital_name
            Picasso.get().load(imgURL + physicianSchedule.hospital.hospital_image).placeholder(R.drawable.placeholder_image).into(itemView.imgPhysicianDetailsHospitalLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhysicianDetailsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_physician_details, parent, false)
        return PhysicianDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return physicianDetailsList.size
    }

    override fun onBindViewHolder(holder: PhysicianDetailsViewHolder, position: Int) {
        return holder.bindPhysicianDetails(physicianDetailsList[position])
    }

    fun updatePhysicianDetails(physicianDetailsList: List<PhysicianSchedule>) {
        this.physicianDetailsList = physicianDetailsList
        notifyDataSetChanged()
    }
}