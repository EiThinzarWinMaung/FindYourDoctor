package com.etzwm.healthcareapp.ui.recommend_suggest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.model.recommend.Recommend
import com.etzwm.healthcareapp.toSimpleString
import kotlinx.android.synthetic.main.item_recommend_suggest.view.*
import java.text.SimpleDateFormat

class RecommendSuggestAdapter(var recommendList: List<Recommend> = ArrayList<Recommend>()): RecyclerView.Adapter<RecommendSuggestAdapter.RecommendSuggestViewHolder>() {

    class RecommendSuggestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(recommend: Recommend) {
            itemView.txtRecommendSuggestName.text = recommend.person_name
            if (recommend.hospital != null) {
                itemView.txtRecommendSuggestHospital.text = recommend.hospital.hospital_name
            }
            itemView.txtRecommendSuggestDate.text = toSimpleString(recommend.rec_date)
//            itemView.txtRecommendSuggestDate.text = recommend.rec_date
            itemView.txtRecommendSuggestText.text = recommend.subject
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendSuggestViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommend_suggest, parent, false)
        return RecommendSuggestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recommendList.size
    }

    override fun onBindViewHolder(holder: RecommendSuggestViewHolder, position: Int) {
        return holder.bind(recommendList[position])
    }

    fun updateRecommendSuggest(recommendList: List<Recommend>) {
        this.recommendList = recommendList
        notifyDataSetChanged()
    }
}