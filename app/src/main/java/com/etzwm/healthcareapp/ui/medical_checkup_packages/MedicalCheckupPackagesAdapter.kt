package com.etzwm.healthcareapp.ui.medical_checkup_packages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.medicalcheckuppackages.Package
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_medical_checkup_packages.view.*
import kotlinx.android.synthetic.main.item_medical_checkup_packages.view.*
import kotlin.math.absoluteValue
import androidx.core.view.marginLeft as marginLeft1

class MedicalCheckupPackagesAdapter(var medicalCheckupList: List<Package> = ArrayList<Package>()): RecyclerView.Adapter<MedicalCheckupPackagesAdapter.MedicalCheckupPackagesViewHolder>() {

    class MedicalCheckupPackagesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(packages: Package) {
//            Picasso.get().load(imgURL + packages.package_image).placeholder(R.drawable.placeholder_image).into(itemView.imgMedicalCheckupPackagesList)
            itemView.txtMedicalCheckupPackagesListName.text = packages.package_name
            itemView.txtMedicalCheckupPackagesListOriginalPrice.text = packages.regular_price
            itemView.txtMedicalCheckupPackagesListPackagePrice.text = packages.special_price

            // Description
            if(packages.package_description != "-") {
                itemView.txtMedicalCheckupPackagesListDescription.visibility = View.VISIBLE
                itemView.txtMedicalCheckupPackagesListDescription.text = packages.package_description
            }
            else {
                itemView.txtMedicalCheckupPackagesListDescription.visibility = View.GONE
            }

            // Regular Price
            if(packages.regular_price != "-") {
                itemView.txtMedicalCheckupPackagesListOriginalPrice.visibility = View.VISIBLE
                itemView.txtMedicalCheckupPackagesListOriginalPrice.text = packages.regular_price
                itemView.txtMedicalCheckupPackagesListOriginalPrice.setBackgroundResource(R.drawable.strike_through_text)
                itemView.txtMedicalCheckupPackagesListPackagePrice.setPadding(10,0,0,0)
            }
            else {
                itemView.txtMedicalCheckupPackagesListOriginalPrice.visibility = View.GONE
            }

            // Special Price
            if(packages.special_price != "-") {
                itemView.txtMedicalCheckupPackagesListPackagePrice.visibility = View.VISIBLE
                itemView.txtMedicalCheckupPackagesListPackagePrice.text = packages.special_price
//                itemView.txtMedicalCheckupPackagesListPackagePrice.setPadding(0,0,0,0)
            }
            else {
                itemView.txtMedicalCheckupPackagesListPackagePrice.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalCheckupPackagesViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_medical_checkup_packages, parent, false)
        return MedicalCheckupPackagesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return medicalCheckupList.size
    }

    override fun onBindViewHolder(holder: MedicalCheckupPackagesViewHolder, position: Int) {
        return holder.bind(medicalCheckupList[position])
    }

    fun updateMedicalCheckupPackagesList(medicalCheckupList: List<Package>) {
        this.medicalCheckupList = medicalCheckupList
        notifyDataSetChanged()
    }
}