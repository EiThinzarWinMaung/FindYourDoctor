package com.etzwm.healthcareapp.ui.find_the_specialists_by_speciality

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient.Companion.imgURL
import com.etzwm.healthcareapp.model.specialities.Speciality
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_find_specialists_by_speciality.view.*

class FindSpecialistsBySpecialityAdapter(var specialityList: List<Speciality> = ArrayList<Speciality>()): RecyclerView.Adapter<FindSpecialistsBySpecialityAdapter.FindSpecialistsBySpecialityViewHolder>() {

    var sclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: FindSpecialistsBySpecialityFragment) {
        this.sclickListener = clickListener
    }

    inner class FindSpecialistsBySpecialityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var speciality: Speciality

        init {
            itemView.setOnClickListener(this)
        }

        fun bindSpeciality(speciality: Speciality) {
            // itemView.txtSearchBySpecialityEN.text = speciality.speciality_ename
            this.speciality = speciality
            itemView.txtSearchBySpecialityMM.text = speciality.speciality_mname
            Picasso.get().load(imgURL + speciality.speciality_image).placeholder(R.drawable.placeholder_image).into(itemView.imgSearchBySpeciality)
        }

        override fun onClick(p0: View?) {
            sclickListener?.onClick(speciality)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindSpecialistsBySpecialityViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_specialists_by_speciality, parent, false)
        return FindSpecialistsBySpecialityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return specialityList.size
    }

    override fun onBindViewHolder(holder: FindSpecialistsBySpecialityViewHolder, position: Int) {
        return holder.bindSpeciality(specialityList[position])
    }

    fun updateSpeciality(specialityList: List<Speciality>) {
        this.specialityList = specialityList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(speciality: Speciality)
    }
}