package com.etzwm.healthcareapp.ui.medical_checkup_packages_by_hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_medical_checkup_packages_by_hospital.view.*

class MedicalCheckupPackagesByHospitalAdapter(var hospitalList: List<Hospital> = ArrayList<Hospital>()): RecyclerView.Adapter<MedicalCheckupPackagesByHospitalAdapter.MedicalCheckupPackagesByHospitalViewHolder>() {

    var mclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: MedicalCheckupPackagesByHospitalFragment) {
        this.mclickListener = clickListener
    }

    inner class MedicalCheckupPackagesByHospitalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var hospital: Hospital

        init {
            itemView.setOnClickListener(this)
        }

        fun bindMedicalCheckupPackagesByHospital(hospital: Hospital) {
            this.hospital = hospital
            itemView.txtMedicalCheckupPackagesByHospital.text = hospital.hospital_name
            Picasso.get().load(imgURL + hospital.hospital_image).placeholder(R.drawable.placeholder_image).into(itemView.imgMedicalCheckupPackagesByHospital)
        }

        override fun onClick(p0: View?) {
            mclickListener?.onClick(hospital)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalCheckupPackagesByHospitalViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_medical_checkup_packages_by_hospital, parent, false)
        return MedicalCheckupPackagesByHospitalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: MedicalCheckupPackagesByHospitalViewHolder, position: Int) {
        return holder.bindMedicalCheckupPackagesByHospital(hospitalList[position])
    }

    fun updateMedicalCheckupPackages(hospitalList: List<Hospital>) {
        this.hospitalList = hospitalList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(hospital: Hospital)
    }
}