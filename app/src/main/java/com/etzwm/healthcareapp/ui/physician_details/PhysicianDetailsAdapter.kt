package com.etzwm.healthcareapp.ui.physician_details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
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

        private var context: Context? = null

        fun bindPhysicianDetails(physicianSchedule: PhysicianSchedule) {
            itemView.txtPhysicianDetailsDateTime.text = physicianSchedule.date_time
            itemView.txtPhysicianDetailsHospitalName.text = physicianSchedule.hospital.hospital_name
            Picasso.get().load(imgURL + physicianSchedule.hospital.hospital_image).placeholder(R.drawable.placeholder_image).into(itemView.imgPhysicianDetailsHospitalLogo)

            itemView.PhysicianDetailsHospitalPhoneCall.setOnClickListener {
                context = itemView.getContext()
                val intent = Intent(Intent.ACTION_DIAL)
                if(physicianSchedule.hospital.hospital_id == 1) {
                    intent.data = Uri.parse("tel:01505284")     // Kan Thar Yar
                }
                if(physicianSchedule.hospital.hospital_id == 2) {
                    intent.data = Uri.parse("tel:09777779004")     // Pinlon
                }
                if(physicianSchedule.hospital.hospital_id == 3) {
                    intent.data = Uri.parse("tel:013684323")     // Pun Hlaing
                }
                if(physicianSchedule.hospital.hospital_id == 4) {
                    intent.data = Uri.parse("tel:012300631")     // Bahosi
                }
                if(physicianSchedule.hospital.hospital_id == 5) {
                    intent.data = Uri.parse("tel:09783666141")     // Victoria
                }
                if(physicianSchedule.hospital.hospital_id == 6) {
                    intent.data = Uri.parse("tel:01500100")     // Thukha Gabar
                }
                if(physicianSchedule.hospital.hospital_id == 7) {
                    intent.data = Uri.parse("tel:019376200")     // Aryu
                }
                if(physicianSchedule.hospital.hospital_id == 8) {
                    intent.data = Uri.parse("tel:012304999")     // Asia Royal
                }
                if(physicianSchedule.hospital.hospital_id == 9) {
                    intent.data = Uri.parse("tel:013551355")    // Aung Yadanar
                }
                if(physicianSchedule.hospital.hospital_id == 10) {
                    intent.data = Uri.parse("tel:012317617")     // Grand Hantha
                }
                if(physicianSchedule.hospital.hospital_id == 11) {
                    intent.data = Uri.parse("tel:01656172")     // OSC
                }
                if(physicianSchedule.hospital.hospital_id == 12) {
                    intent.data = Uri.parse("tel:018603619")     // SSC
                }
                context?.startActivity(intent)
            }
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